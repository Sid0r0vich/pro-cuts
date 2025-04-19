package com.sidor.procuts.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sidor.procuts.data.CutDate
import com.sidor.procuts.data.cutDatesList
import com.sidor.procuts.data.cutNamesToId
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Date

enum class CutQuestionnaireScreenType {
    DateName,
    Age,
    CutFrequency,
    HeadForm,
    HairStruct,
    HairThickness,
    HairLen,
    ScalpType,
    Add
}

open class CutQuestionnaireViewModel : ViewModel() {
    data class UiState(
        val screenType: CutQuestionnaireScreenType,
        val date: Date = Date(),
        val paramsMap: MutableMap<String, String> = mutableMapOf()
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

    fun navigateNext() {
        val next = (_uiState.value.screenType.ordinal + 1) % CutQuestionnaireScreenType.entries.size
        navigate(CutQuestionnaireScreenType.entries[next])
    }

    fun navigateBack() {
        var next = (_uiState.value.screenType.ordinal - 1) % CutQuestionnaireScreenType.entries.size
        if (next < 0) next += CutQuestionnaireScreenType.entries.size
        navigate(CutQuestionnaireScreenType.entries[next])
    }

    fun addCut() {
        val cutId = cutNamesToId[getParam("cutName")]
        if (cutId != null) {
            cutDatesList.add(CutDate(cutId = cutId, date = uiState.value.date))
        }
    }

    companion object {
        val paramNames = listOf(
            "cutFrequency",
            "age",
            "headForm",
            "hairStruct",
            "hairThickness",
            "hairLen",
            "scalpType"
        )
    }
}