package com.sidor.procuts.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.sidor.procuts.data.CutDate
import com.sidor.procuts.data.cutDatesList
import com.sidor.procuts.data.cutNamesToId
import com.sidor.procuts.ui.screens.screentypes.CutQuestionnaireScreenType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Date

open class CutQuestionnaireViewModel : ViewModel() {
    data class UiState(
        val screenType: CutQuestionnaireScreenType,
        val date: Date = Date(),
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

    fun setParam(name: String, value: String) {
        _uiState.value.paramsMap[name] = value
    }

    fun getParam(name: String): String {
        return _uiState.value.paramsMap[name] ?: ""
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

    fun addCut() {
        val cutId = cutNamesToId[getParam("cutName")]
        if (cutId != null) {
            cutDatesList[cutDatesList.size] = CutDate(cutId = cutId, date = _uiState.value.date, cutParams = _uiState.value.paramsMap)
            _uiState.value.paramsMap = mutableMapOf()
        }
    }
}