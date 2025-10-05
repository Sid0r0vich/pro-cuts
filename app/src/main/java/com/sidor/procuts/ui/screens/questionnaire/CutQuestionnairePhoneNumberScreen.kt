package com.sidor.procuts.ui.screens.questionnaire

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sidor.procuts.R
import com.sidor.procuts.ui.components.PaddingSpaces
import com.sidor.procuts.ui.components.PhoneNumberField
import com.sidor.procuts.ui.screens.DefaultPaddingScreenWithQuestionnaireButtons
import com.sidor.procuts.ui.screens.DefaultSpacer
import com.sidor.procuts.ui.screens.TopAppBarScreen
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar
import com.sidor.procuts.utils.PhoneNumberParser


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CutQuestionnairePhoneNumberScreen(
    onBack: () -> Unit,
    onNext: (String) -> Unit,
    phoneNumber: String? = null,
    onSetPhoneNumber: (String) -> Unit,
    phoneNumberIsExists: Boolean,
) {
    var phoneNumber by rememberSaveable { mutableStateOf<String?>(phoneNumber) }
    var phoneNumberIsValid by rememberSaveable {
        mutableStateOf(phoneNumber?.let { PhoneNumberParser.parsePhoneNumber(it) } != null)
    }

    TopAppBarScreen(
        topBar = {
            TitleTopAppBar(
                title = stringResource(R.string.add_haircut_tab_app_bar),
                onBack = onBack
            )
        },
    ) {
        DefaultPaddingScreenWithQuestionnaireButtons(
            paddingSpaces = PaddingSpaces(horizontal = 2, top = 2, bottom = 1),
            onNext = { phoneNumber?.let { onNext(it) } },
            enabled = phoneNumberIsValid && phoneNumberIsExists
        ) {
            Text(
                text = stringResource(R.string.client_phone_number),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 5.dp)
            )
            DefaultSpacer(1)
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
                },
                isError = !phoneNumberIsExists
            )
        }
    }
}