package com.sidor.procuts.ui.screens.questionnaire

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sidor.procuts.R
import com.sidor.procuts.data.ClientDTO
import com.sidor.procuts.ui.PaddingSpaces
import com.sidor.procuts.ui.ToastNotifier
import com.sidor.procuts.ui.screens.DefaultPaddingScreenWithQuestionnaireButtons
import com.sidor.procuts.ui.screens.DefaultSpacer
import com.sidor.procuts.ui.screens.DpSpacer
import com.sidor.procuts.ui.screens.TopAppBarScreen
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar
import com.sidor.procuts.ui.theme.LocalColorPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CutQuestionnaireClientChoiceScreen(
    onBack: () -> Unit,
    onNext: (ClientDTO) -> Unit,
    clients: List<ClientDTO>,
    defaultValue: String
) {
    var textValue by rememberSaveable { mutableStateOf(defaultValue) }
    val clientNames = clients.map { client -> client.getFullName() }
    val ctx = LocalContext.current
    val textIsEmptyMessage = stringResource(R.string.empty_client_field_message)
    val clientIsNotFoundMessage = stringResource(R.string.client_not_found_message)

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
            onNext = {
                val notifier = ToastNotifier(ctx)
                if (textValue.isEmpty()) {
                    notifier.show(message = textIsEmptyMessage)
                } else {
                    clients.firstOrNull() { client ->
                        client.getFullName() == textValue
                    }?.let { clientDTO ->
                        onNext(clientDTO)
                    }?: notifier.show(message = clientIsNotFoundMessage)
                }
            },
            enabled = textValue.isNotEmpty()
        ) {
            Text(
                text = stringResource(R.string.client_name),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 5.dp)
            )
            DefaultSpacer(1)

            ClientSelectionField(
                value = textValue,
                clients = clientNames.filter { client ->
                    if (textValue.isNotEmpty()) {
                        client.lowercase()
                            .contains(textValue.lowercase())
                    } else true
                },
                onValueChanged = { name -> textValue = name }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientSelectionField(
    value: String,
    clients: List<String>,
    onValueChanged: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = { value ->
            onValueChanged(value)
            expanded = true
        },
        colors = ExposedDropdownMenuDefaults.textFieldColors(
            focusedIndicatorColor = LocalColorPalette.current.darkFontColor,
            focusedContainerColor = LocalColorPalette.current.mainColor,
            unfocusedContainerColor = LocalColorPalette.current.mainColor
        ),
        modifier = Modifier.fillMaxWidth()
            .onFocusChanged { focusState ->
                expanded = focusState.isFocused
            }
    )
    if (expanded) {
        LazyColumn {
            clients.forEach { client ->
                item {
                    DpSpacer(3)
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onValueChanged(client)
                                expanded = false
                            },
                        shape = RoundedCornerShape(4.dp),
                        color = Color.White,
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(12.dp),
                        ) {
                            Text(text = client)
                        }
                    }
                }
            }
        }
    }
}