package com.sidor.procuts.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.sidor.procuts.R
import com.sidor.procuts.data.cutFrequencyList
import com.sidor.procuts.ui.QuestionnaireDropdownMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CutQuestionnaireSecondScreen(
    onBack: () -> Unit,
    onNext: () -> Unit,
    onCutFrequencyChange: (String) -> Unit
) {
    val menuList = cutFrequencyList.map { stringResource(it) }

    var cutFrequency by remember { mutableStateOf("") }
    onCutFrequencyChange(cutFrequency)

    DefaultCutQuestionnaireScreen(
        onBack = onBack,
        onNext = onNext
    ) {
        Text(
            text = stringResource(R.string.question_cut_frequency),
            style = MaterialTheme.typography.titleLarge
        )
        DefaultSpacer(2)
        QuestionnaireDropdownMenu(
            name = stringResource(R.string.cut_frequency),
            value = cutFrequency,
            onValueChanged = {
                cutFrequency = it
                onCutFrequencyChange(it)
            },
            menuList = menuList
        )
    }
}

