package com.sidor.procuts.ui.viewmodels

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sidor.procuts.data.ClientRepository
import com.sidor.procuts.data.CutDateRepository
import com.sidor.procuts.data.CutRepository
import com.sidor.procuts.data.models.ClientDTO
import com.sidor.procuts.data.models.CutDTO
import com.sidor.procuts.data.models.CutDateDTO
import com.sidor.procuts.data.models.CutDateInfoDTO
import com.sidor.procuts.data.models.defaultCutDTO
import com.sidor.procuts.network.googleforms.QuestionOptions
import com.sidor.procuts.network.infer.CutRecommendation
import com.sidor.procuts.network.infer.InferCutRecommendationsApi
import com.sidor.procuts.ui.screens.screentypes.CutQuestionnaireScreenType
import com.sidor.procuts.ui.viewmodels.HomeViewModel.Companion.TIMEOUT_MILLIS
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.util.Date
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi


enum class AddResult {
    SUCCESS,
    CUT_NAME_IS_NOT_FOUND,
    CLIENT_IS_NOT_FOUND
}

sealed interface RecommendationsUIState {
    data class Success(val recommendations: List<CutRecommendation>) : RecommendationsUIState
    data class Error(val message: String) : RecommendationsUIState
    object Loading : RecommendationsUIState
}

sealed interface QuestionnaireUIState {
    data class Success(val questions: List<QuestionOptions>) : QuestionnaireUIState
    data class Error(val message: String) : QuestionnaireUIState
    object Loading : QuestionnaireUIState
}

@HiltViewModel
open class CutQuestionnaireViewModel @Inject constructor(
    val cutRepository: CutRepository,
    val clientRepository: ClientRepository,
    val cutDateRepository: CutDateRepository
) : ViewModel() {
    data class UiState(
        var screenType: CutQuestionnaireScreenType,
        var questionInd: Int = 0,
        var clientDTO: ClientDTO? = null,
        var date: Date = Date(),
        var cutId: Int? = null,
        var cutRecommendations: List<StateFlow<CutDTO>>? = null,
        var recentCuts: List<StateFlow<CutDTO>>? = null,
        var questionList: List<QuestionOptions>? = null,
        var paramsMap: MutableMap<String, String> = mutableMapOf(),
        var clients: StateFlow<Map<Int, StateFlow<ClientDTO>>>,
        val photoUri: Uri? = null
    )

    var recommendationsUIState: RecommendationsUIState by mutableStateOf(RecommendationsUIState.Loading)
        private set

    var questionnaireUIState: QuestionnaireUIState by mutableStateOf(QuestionnaireUIState.Loading)
        private set

    init {
        requestForm()
        viewModelScope.launch {
            clientRepository.loadClients()
        }
    }

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(
        UiState(
            screenType = CutQuestionnaireScreenType.entries.first(),
            clients = clientRepository.getClientsStateFlow()
        )
    )
    val uiState: StateFlow<UiState> get() = _uiState

    fun resetUIState() {
        _uiState.value =
            UiState(
                screenType = CutQuestionnaireScreenType.entries.first(),
                clients = clientRepository.getClientsStateFlow()
            )
    }

    fun setDate(date: Date) {
        _uiState.value = _uiState.value.copy(date = date)
    }

    fun setParam(name: String, value: String) {
        val newParamsMap = _uiState.value.paramsMap.toMutableMap()
        newParamsMap[name] = value
        _uiState.value = _uiState.value.copy(paramsMap = newParamsMap)
    }

    fun setClientDTO(clientDTO: ClientDTO?) {
        _uiState.value = _uiState.value.copy(clientDTO = clientDTO)
    }

    fun setCutRecommendations(cutRecommendations: List<StateFlow<CutDTO>>) {
        _uiState.value = _uiState.value.copy(cutRecommendations = cutRecommendations)
    }

    fun setRecentCuts(recentCutIds: List<Int>) {
        val recentCuts = cutRepository.getCutsStateFlowsByIds(recentCutIds, viewModelScope)
        _uiState.value = _uiState.value.copy(recentCuts = recentCuts)
    }

    fun setCutId(cutId: Int) {
        _uiState.value = _uiState.value.copy(cutId = cutId)
    }


    fun setQuestionInd(questionInd: Int) {
        _uiState.value = _uiState.value.copy(questionInd = questionInd)
    }

    fun setPhotoUri(photoUri: Uri) {
        _uiState.value = _uiState.value.copy(photoUri = photoUri)
    }

    fun resetQuestionInd() {
        _uiState.value = _uiState.value.copy(questionInd = 0)
    }

    fun getCut(): StateFlow<CutDTO?>? {
        return _uiState.value.cutId?.let {
            cutRepository
                .getCutStream(it)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                    initialValue = defaultCutDTO

                )
        }
    }

    fun navigate(screenType: CutQuestionnaireScreenType) {
        _uiState.value = _uiState.value.copy(screenType = screenType)
    }

    fun getNextScreen(cutQuestionnaireScreenType: CutQuestionnaireScreenType): CutQuestionnaireScreenType {
        val next =
            (cutQuestionnaireScreenType.ordinal + 1) % CutQuestionnaireScreenType.entries.size
        return CutQuestionnaireScreenType.entries[next]
    }

    fun getPrevScreen(cutQuestionnaireScreenType: CutQuestionnaireScreenType): CutQuestionnaireScreenType {
        var next = cutQuestionnaireScreenType.ordinal - 1
        if (next < 0) next += CutQuestionnaireScreenType.entries.size
        return CutQuestionnaireScreenType.entries[next]
    }

    @OptIn(ExperimentalEncodingApi::class)
    fun tryAddCut(
        context: Context,
        onAddClick: (CutDateInfoDTO) -> Unit
    ): AddResult {
        val cutId = _uiState.value.cutId
        val clientId = _uiState.value.clientDTO?.id

        val allCutIds = cutRepository.getAll().map { cut -> cut.id }
        if (cutId == null || !allCutIds.contains(cutId)) return AddResult.CUT_NAME_IS_NOT_FOUND
        if (clientId == null) return AddResult.CLIENT_IS_NOT_FOUND

        val photo = _uiState.value.photoUri?.path?.let { path ->
            File(path).readBytes()
        }
        Log.d("URI", _uiState.value.photoUri.toString())
        Log.d("PHOTO", photo.toString())

        onAddClick(
            CutDateInfoDTO(
                cutId = cutId,
                clientId = clientId,
                date = _uiState.value.date,
                cutPhoto = photo?.let { Base64.encode(it) },
                cutParams = _uiState.value.paramsMap
            )
        )
        _uiState.value = _uiState.value.copy(paramsMap = mutableMapOf())

        return AddResult.SUCCESS
    }

    fun requestCutRecommendations() {
        viewModelScope.launch {
            recommendationsUIState = RecommendationsUIState.Loading
            recommendationsUIState = try {
                val listResult = InferCutRecommendationsApi.retrofitService.getRecommendations(
                    uiState.value.paramsMap
                ).predictions

                setCutRecommendations(
                    cutRepository.getCutsStateFlowsByNames(
                        listResult.map { cutRecommendation -> cutRecommendation.style },
                        viewModelScope
                    )
                )
                RecommendationsUIState.Success(listResult)
            } catch (e: IOException) {
                Log.e("RETROFIT", e.toString())
                RecommendationsUIState.Error(message = e.toString())
            }
        }
    }

    fun requestForm() {
        viewModelScope.launch {
            questionnaireUIState = QuestionnaireUIState.Loading
            questionnaireUIState = try {
                val questionList =
                    InferCutRecommendationsApi.retrofitService.getOptions().toQuestionList()
                if (questionList.size >= 2) QuestionnaireUIState.Success(questionList.drop(1))
                else QuestionnaireUIState.Error(message = "empty question list!")
            } catch (e: Exception) {
                Log.e("RETROFIT", e.toString())
                QuestionnaireUIState.Error(message = e.toString())
            }
        }
    }

    fun getClientCutDates(
        clientId: Int?,
    ): List<StateFlow<CutDateDTO>> = clientId?.let {
        cutDateRepository.getAllCutsWithClientId(it)
    } ?: listOf()
}

fun CutRepository.getCutsStateFlowsByIds(
    cutIds: List<Int>,
    scope: CoroutineScope,
): List<StateFlow<CutDTO>> {
    return this.getCutsByIds(cutIds).map { flow ->
        flow.stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = defaultCutDTO
        )
    }
}

fun CutRepository.getCutsStateFlowsByNames(
    cutNames: List<String>,
    scope: CoroutineScope,
): List<StateFlow<CutDTO>> {
    return this.getCutsByNames(cutNames).map { flow ->
        flow.stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = defaultCutDTO
        )
    }
}