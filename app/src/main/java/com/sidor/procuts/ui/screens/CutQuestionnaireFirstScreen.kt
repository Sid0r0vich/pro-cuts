package com.sidor.procuts.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R
import com.sidor.procuts.data.allCuts
import com.sidor.procuts.data.cutNamesToId
import com.sidor.procuts.ui.DatePickerDocked
import com.sidor.procuts.ui.QuestionnaireDropdownMenu
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CutQuestionnaireFirstScreen(
    onBack: () -> Unit,
    onNext: () -> Unit,
    onDateChange: (Date) -> Unit,
    onNameChange: (String) -> Unit
) {
    var date by remember { mutableStateOf<Date>(Date()) }
    onDateChange(date)

    var cutName by remember { mutableStateOf(allCuts[0]?.name ?: "") }
    onNameChange(cutName)

    DefaultCutQuestionnaireScreen(
        onBack = onBack,
        onNext = onNext
    ) {
        QuestionnaireDropdownMenu(
            name = stringResource(R.string.cut_name),
            value = cutName,
            onValueChanged = {
                cutName = it
                onNameChange(it)
            },
            menuList = cutNamesToId.keys.sorted()
        )

        DefaultSpacer()
        DatePickerDocked(
            selectedDate = date
        ) {
            onDateChange(date)
        }

    }
}

