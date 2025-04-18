package com.sidor.procuts.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sidor.procuts.R
import com.sidor.procuts.data.Client
import com.sidor.procuts.ui.RectangleTextField
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddClientScreen(
    onBack: () -> Unit,
    onAddClient: (Client) -> Unit
) {
    var lastName by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }
    var noMiddleName by remember { mutableStateOf(false) }

    var photo: Painter? by remember { mutableStateOf(null) }

    TopAppBarScreen(
        topBar = {
            TitleTopAppBar(
                title = stringResource(R.string.add_client_tab_app_bar),
                onBack = onBack
            )
        },
    ) {
        LazyPaddingScreen(
            horizontalSpaceCount = 4,
            verticalSpaceCount = 2,
        ) {
            item {
                RectangleTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = stringResource(R.string.first_name),
                )
            }

            item {
                DefaultSpacer()
                RectangleTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = stringResource(R.string.last_name),
                )
            }

            item {
                DefaultSpacer()
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RectangleTextField(
                        value = middleName,
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
                            if (it) middleName = ""
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.Black
                        )
                    )
                    Text(text = stringResource(R.string.no_middle_name))
                }
            }

            item {
                DefaultSpacer(2)
                Text(text = stringResource(R.string.photo))
                DefaultSpacer()
                Box(
                    modifier = Modifier
                        .clickable {}
                        .size(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (photo != null) {
                        Image(
                            painter = photo!!,
                            contentDescription = stringResource(R.string.client_photo),
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Surface(
                            color = Color.LightGray,
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text("+", style = MaterialTheme.typography.titleLarge, color=Color.DarkGray)
                            }
                        }
                    }
                }
            }

            item {
                DefaultSpacer(2)
                Button(
                    onClick = { onAddClient(
                        Client(
                            firstName = firstName,
                            lastName = lastName,
                            middleName = middleName
                        )
                    ) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = lastName.isNotBlank() && firstName.isNotBlank() && (noMiddleName || middleName.isNotBlank()),
                    shape = RectangleShape
                ) {
                    Text(stringResource(R.string.create_client))
                }
            }
        }
    }
}