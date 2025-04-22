package com.sidor.procuts.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import com.google.i18n.phonenumbers.Phonenumber
import com.sidor.procuts.R
import com.sidor.procuts.data.cutNamesToId
import com.sidor.procuts.data.nameToLabelId
import com.sidor.procuts.ui.DatePickerDocked
import com.sidor.procuts.ui.PaddingSpaces
import com.sidor.procuts.ui.PhoneNumberField
import com.sidor.procuts.ui.QuestionnaireDropdownMenu
import com.sidor.procuts.ui.TextWithBoldField
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar
import com.sidor.procuts.ui.theme.LocalColorPalette
import com.sidor.procuts.utils.PhoneNumberParser
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CutQuestionnaireFirstScreen(
    onBack: () -> Unit,
    onNext: () -> Unit,
    onDateChange: (Date) -> Unit,
    onNameChange: (String) -> Unit,
    value: String,
    date: Date
) {
    var date by remember { mutableStateOf<Date>(date) }
    onDateChange(date)

    var cutName by remember { mutableStateOf(value) }
    onNameChange(cutName)

    DefaultCutQuestionnaireScreen(
        onBack = onBack,
        onNext = onNext,
        enabled = cutNamesToId.contains(cutName)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CutQuestionnaireLastScreen(
    onBack: () -> Unit,
    onAddCut: () -> Unit,
    onSetPhoneNumber: (String) -> Unit,
    cutParams: Map<String, String>
) {
    var phoneNumber by rememberSaveable { mutableStateOf<String?>(null) }
    var phoneNumberIsValid by rememberSaveable { mutableStateOf(false) }

    TopAppBarScreen(
        topBar = {
            TitleTopAppBar(
                title = stringResource(R.string.add_haircut_tab_app_bar),
                onBack = onBack
            )
        },
    ) {
        LazyPaddingScreen(
            paddingSpaces = PaddingSpaces(2)
        ) {
            cutParams.forEach { (param, value) ->
                item {
                    TextWithBoldField(
                        field = stringResource(nameToLabelId[param] ?: R.string.none),
                        value = value,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }

            item {
                DefaultSpacer(2)
                PhoneNumberField(
                    phoneNumber,
                    onPhoneNumberChange = { number ->
                        phoneNumber = number
                        val parsedNumber = PhoneNumberParser.parsePhoneNumber(number)
                        if (parsedNumber != null) {
                            onSetPhoneNumber(number)
                            phoneNumberIsValid = true
                        } else {
                            phoneNumberIsValid = false
                        }
                    }
                )
            }

            item {
                DefaultSpacer(2)
                Button(
                    onClick = onAddCut,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LocalColorPalette.current.buttonColor
                    ),
                    enabled = phoneNumberIsValid
                ) {
                    Text(stringResource(R.string.create_haircut))
                }
            }
        }
    }
}