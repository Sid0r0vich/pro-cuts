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
    Parameters,
    Add
}

open class CutQuestionnaireViewModel : ViewModel() {
    data class UiState(
        val screenType: CutQuestionnaireScreenType,
        val cutName: String? = null,
        val date: Date? = null,
        val cutFrequency: String? = null
    )

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState(CutQuestionnaireScreenType.DateName))
    val uiState: StateFlow<UiState> get() = _uiState

    fun setDate(date: Date) {
        _uiState.value = _uiState.value.copy(date = date)
    }

    fun setCutName(cutName: String) {
        _uiState.value = _uiState.value.copy(cutName = cutName)
    }

    fun setCutFrequency(cutFrequency: String) {
        _uiState.value = _uiState.value.copy(cutFrequency = cutFrequency)
    }

    fun navigate(screenType: CutQuestionnaireScreenType) {
        _uiState.value = _uiState.value.copy(screenType = screenType)
    }

    fun navigateDateName() = navigate(CutQuestionnaireScreenType.DateName)
    fun navigateParameters() = navigate(CutQuestionnaireScreenType.Parameters)
    fun navigateAdd() = navigate(CutQuestionnaireScreenType.Add)

    fun addCut() {
        val cutId = cutNamesToId[uiState.value.cutName]
        Log.d("A", "$cutId ${uiState.value.date}")
        if (cutId != null && uiState.value.date != null) {
            cutDatesList.add(CutDate(cutId = cutId, date = uiState.value.date!!))
        }
    }
}