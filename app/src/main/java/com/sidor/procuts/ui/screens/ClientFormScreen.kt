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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R
import com.sidor.procuts.data.Client
import com.sidor.procuts.data.cliensList
import com.sidor.procuts.ui.ImagePicker
import com.sidor.procuts.ui.PaddingSpaces
import com.sidor.procuts.ui.RectangleTextField
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar
import com.sidor.procuts.ui.theme.LocalColorPalette

data class DefaultClientForm(
    val clientId: Int,
    val lastName: String,
    val firstName: String,
    val middleName: String?,
    val noMiddleName: Boolean,
    val clientPhoto: ByteArray?
)

@Composable
fun AddClientScreen(
    onBack: () -> Unit,
    onAddClient: (Client) -> Unit
) {
    ClientFormScreen(
        defaultClientForm = DefaultClientForm(
            clientId = cliensList.size,
            lastName = "",
            firstName = "",
            middleName = null,
            noMiddleName = false,
            clientPhoto = null
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
    client: Client?,
    onEditClient: (Client) -> Unit
) {
    ClientFormScreen(
        defaultClientForm = DefaultClientForm(
            clientId = client?.id ?: cliensList.size,
            lastName = client?.lastName ?: "",
            firstName = client?.firstName ?: "",
            middleName = client?.middleName,
            noMiddleName = client != null && client.middleName == null,
            clientPhoto = client?.photo
        ),
        topBarTitleText = stringResource(R.string.edit_client_tab_app_bar),
        buttonText = stringResource(R.string.edit_client),
        onBack = onBack,
        onClickButton = onEditClient
    )
}

@Composable
fun ClientFormScreen(
    defaultClientForm: DefaultClientForm,
    topBarTitleText: String,
    buttonText: String,
    onBack: () -> Unit,
    onClickButton: (Client) -> Unit
) {
    var lastName by remember { mutableStateOf(defaultClientForm.lastName) }
    var firstName by remember { mutableStateOf(defaultClientForm.firstName) }
    var middleName by remember { mutableStateOf<String?>(defaultClientForm.middleName) }
    var noMiddleName by remember { mutableStateOf(defaultClientForm.noMiddleName) }
    var clientPhoto by remember { mutableStateOf<ByteArray?>(defaultClientForm.clientPhoto) }

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
                        Client(
                            id = defaultClientForm.clientId,
                            firstName = firstName,
                            lastName = lastName,
                            middleName = middleName,
                            photo = clientPhoto
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
            RectangleTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = stringResource(R.string.first_name),
            )

            DefaultSpacer()
            RectangleTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = stringResource(R.string.last_name),
            )

            DefaultSpacer()
            Row(verticalAlignment = Alignment.CenterVertically) {
                RectangleTextField(
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