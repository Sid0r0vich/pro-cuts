package com.sidor.procuts.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Date

enum class CutQuestionnaireScreenType {
    DateName,
    Parameters
}

open class CutQuestionnaireViewModel : ViewModel() {
    data class UiState(
        val screenType: CutQuestionnaireScreenType,
        val cutName: String? = null,
        val date: Date? = null,
    )

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState(CutQuestionnaireScreenType.DateName))
    val uiState: StateFlow<UiState> get() = _uiState

    fun navigate(screenType: CutQuestionnaireScreenType) {
        _uiState.value = _uiState.value.copy(screenType = screenType)
    }

    fun navigateDateName() = navigate(CutQuestionnaireScreenType.DateName)
    fun navigateParameters() = navigate(CutQuestionnaireScreenType.Parameters)
}