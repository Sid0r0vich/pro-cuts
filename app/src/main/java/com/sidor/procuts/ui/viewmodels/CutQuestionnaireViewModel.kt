package com.sidor.procuts.ui.viewmodels

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.sidor.procuts.data.CutDTO
import com.sidor.procuts.data.CutDateInfoDTO
import com.sidor.procuts.data.allCuts
import com.sidor.procuts.data.cutNamesToId
import com.sidor.procuts.ui.screens.screentypes.CutQuestionnaireScreenType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Date

enum class AddResult {
    SUCCESS,
    CUT_NAME_IS_NOT_FOUND,
    PHONE_NUMBER_IS_NOT_FOUND
}

open class CutQuestionnaireViewModel() : ViewModel() {
    data class UiState(
        val screenType: CutQuestionnaireScreenType,
        val clientId: Int? = null,
        val date: Date = Date(),
        var clientPhoneNumber: String? = null,
        var photoUri: Uri? = null,
        var cutId: Int? = null,
        var paramsMap: MutableMap<String, String> = mutableMapOf()
    )

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState(CutQuestionnaireScreenType.DateName))
    val uiState: StateFlow<UiState> get() = _uiState

    fun setDate(date: Date) {
        _uiState.value = _uiState.value.copy(date = date)
    }

    fun getDate(): Date {
        return _uiState.value.date
    }

    fun setPhoneNumber(phoneNumber: String) {
        _uiState.value.clientPhoneNumber = phoneNumber
    }

    fun setParam(name: String, value: String) {
        _uiState.value.paramsMap[name] = value
    }

    fun getParam(name: String): String {
        return _uiState.value.paramsMap[name] ?: ""
    }

    fun setClientId(clientId: Int?) {
        _uiState.value = _uiState.value.copy(clientId = clientId)
    }

    fun getCut(): CutDTO? {
        return allCuts[_uiState.value.cutId]
    }

    fun setCutId(cutId: Int) {
        _uiState.value = _uiState.value.copy(cutId = cutId)
    }

    fun setPhotoUri(photoUri: Uri) {
        _uiState.value = _uiState.value.copy(photoUri = photoUri)
    }

    fun navigate(screenType: CutQuestionnaireScreenType) {
        _uiState.value = _uiState.value.copy(screenType = screenType)
    }

    fun getNextScreen(cutQuestionnaireScreenType: CutQuestionnaireScreenType): CutQuestionnaireScreenType {
        val next = (cutQuestionnaireScreenType.ordinal + 1) % CutQuestionnaireScreenType.entries.size
        return CutQuestionnaireScreenType.entries[next]
    }

    fun getPrevScreen(cutQuestionnaireScreenType: CutQuestionnaireScreenType): CutQuestionnaireScreenType {
        var next = cutQuestionnaireScreenType.ordinal - 1
        if (next < 0) next += CutQuestionnaireScreenType.entries.size
        return CutQuestionnaireScreenType.entries[next]
    }

    fun tryAddCut(
        getClientIdOnPhoneNumber: (String) -> Int?,
        onAddClick: (CutDateInfoDTO) -> Unit
    ): AddResult {
        val cutId = _uiState.value.cutId
        val clientId = _uiState.value.clientPhoneNumber?.let { getClientIdOnPhoneNumber(it.toString()) }
        setClientId(clientId)

        if (cutId == null || !allCuts.contains(cutId)) return AddResult.CUT_NAME_IS_NOT_FOUND
        if (clientId == null) return AddResult.PHONE_NUMBER_IS_NOT_FOUND

        onAddClick(
            CutDateInfoDTO(
                cutId = cutId,
                clientId = clientId,
                date = _uiState.value.date,
                cutPhoto = _uiState.value.photoUri,
                cutParams = _uiState.value.paramsMap
            )
        )
        _uiState.value.paramsMap = mutableMapOf()

        return AddResult.SUCCESS
    }
}