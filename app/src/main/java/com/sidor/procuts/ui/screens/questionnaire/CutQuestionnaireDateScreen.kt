package com.sidor.procuts.ui.screens.questionnaire

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sidor.procuts.ui.DatePickerDocked
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CutQuestionnaireDateScreen(
    onBack: () -> Unit,
    onNext: () -> Unit,
    onDateChange: (Date) -> Unit,
    date: Date
) {
    var date by remember { mutableStateOf<Date>(date) }
    onDateChange(date)

    DefaultCutQuestionnaireScreen(
        onBack = onBack,
        onNext = onNext,
        enabled = true
    ) {
        DatePickerDocked(
            selectedDate = date
        ) {
            onDateChange(date)
        }
    }
}
