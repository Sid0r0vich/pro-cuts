package com.sidor.procuts.ui.screens


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R
import com.sidor.procuts.data.ClientDTO
import com.sidor.procuts.data.ClientInfoDTO
import com.sidor.procuts.ui.ImagePicker
import com.sidor.procuts.ui.PaddingSpaces
import com.sidor.procuts.ui.MyStyledTextField
import com.sidor.procuts.ui.PhoneNumberField
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar
import com.sidor.procuts.ui.theme.LocalColorPalette
import com.sidor.procuts.utils.PhoneNumberParser

data class DefaultClientForm(
    val lastName: String,
    val firstName: String,
    val middleName: String?,
    val noMiddleName: Boolean,
    val clientPhoto: ByteArray?,
    val clientPhoneNumber: String?
)

@Composable
fun AddClientScreen(
    onBack: () -> Unit,
    onAddClient: (ClientInfoDTO) -> Unit
) {
    ClientFormScreen(
        defaultClientForm = DefaultClientForm(
            lastName = "",
            firstName = "",
            middleName = null,
            noMiddleName = false,
            clientPhoto = null,
            clientPhoneNumber = null
        ),
        topBarTitleText = stringResource(R.string.create_client_tab_app_bar),
        buttonText = stringResource(R.string.create_client),
        onBack = onBack,
        onClickButton = onAddClient
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditClientScreen(
    onBack: () -> Unit,
    clientDTO: ClientDTO,
    onEditClient: (ClientDTO) -> Unit
) {
    ClientFormScreen(
        defaultClientForm = DefaultClientForm(
            lastName = clientDTO.lastName,
            firstName = clientDTO.firstName,
            middleName = clientDTO.middleName,
            noMiddleName = clientDTO.middleName == null,
            clientPhoto = clientDTO.photo,
            clientPhoneNumber = clientDTO.phoneNumber
        ),
        topBarTitleText = stringResource(R.string.edit_client_tab_app_bar),
        buttonText = stringResource(R.string.edit_client),
        onBack = onBack,
        onClickButton = { clientInfoDTO -> onEditClient(clientInfoDTO.withId(clientDTO.id)) }
    )
}

@Composable
fun ClientFormScreen(
    defaultClientForm: DefaultClientForm,
    topBarTitleText: String,
    buttonText: String,
    onBack: () -> Unit,
    onClickButton: (ClientInfoDTO) -> Unit
) {
    var lastName by rememberSaveable { mutableStateOf(defaultClientForm.lastName) }
    var firstName by rememberSaveable { mutableStateOf(defaultClientForm.firstName) }
    var middleName by rememberSaveable { mutableStateOf<String?>(defaultClientForm.middleName) }
    var noMiddleName by rememberSaveable { mutableStateOf(defaultClientForm.noMiddleName) }
    var clientPhoto by rememberSaveable { mutableStateOf<ByteArray?>(defaultClientForm.clientPhoto) }
    var phoneNumber by rememberSaveable { mutableStateOf<String?>(defaultClientForm.clientPhoneNumber) }
    var phoneNumberIsValid by rememberSaveable { mutableStateOf(false) }

    TopAppBarScreen(
        topBar = {
            TitleTopAppBar(
                title = topBarTitleText,
                onBack = onBack
            )
        },
    ) {
        PaddingScreenWithBottomButtons(
            buttons = {
                Button(
                    onClick = { onClickButton(
                        ClientInfoDTO(
                            firstName = firstName,
                            lastName = lastName,
                            middleName = middleName,
                            photo = clientPhoto,
                            phoneNumber = phoneNumber.toString()
                        )
                    ) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = lastName.isNotBlank() && firstName.isNotBlank() && (noMiddleName || !middleName.isNullOrEmpty()),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LocalColorPalette.current.buttonColor
                    )
                ) {
                    Text(buttonText)
                }
            },
            paddingSpaces = PaddingSpaces(horizontal = 2, vertical = 1)
        ) {
            PhoneNumberField(
                phoneNumber,
                onPhoneNumberChange = { number ->
                    phoneNumber = number
                    val parsedNumber = PhoneNumberParser.parsePhoneNumber(number)
                    if (parsedNumber != null) {
                        phoneNumberIsValid = true
                    } else {
                        phoneNumberIsValid = false
                    }
                }
            )

            DefaultSpacer()
            MyStyledTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = stringResource(R.string.first_name),
            )

            DefaultSpacer()
            MyStyledTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = stringResource(R.string.last_name),
            )

            DefaultSpacer()
            Row(verticalAlignment = Alignment.CenterVertically) {
                MyStyledTextField(
                    value = middleName ?: "",
                    onValueChange = { middleName = it },
                    label = stringResource(R.string.middle_name),
                    modifier = Modifier.weight(1f),
                    enabled = !noMiddleName,
                )
                DefaultSpacer()
                Checkbox(
                    checked = noMiddleName,
                    onCheckedChange = {
                        noMiddleName = it
                        if (it) middleName = null
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Black
                    )
                )
                Text(text = stringResource(R.string.no_middle_name))
            }

            DefaultSpacer(2)
            Text(text = stringResource(R.string.photo))
            DefaultSpacer()
            ImagePicker(
                clientImage = clientPhoto,
                onImageLoad =  { clientPhoto = it }
            )
        }
    }
}