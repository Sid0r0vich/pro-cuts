package com.sidor.procuts.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R
import com.sidor.procuts.data.allCuts
import com.sidor.procuts.data.cutNamesToId
import com.sidor.procuts.ui.CutForm
import com.sidor.procuts.ui.DatePickerDocked
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCutScreen(
    onBack: () -> Unit,
    onAddCut: (CutForm) -> Unit
) {
    var date = remember { mutableStateOf<Date>(Date()) }

    var cutName by remember { mutableStateOf(allCuts[0]?.name ?: "") }
    var expanded by remember { mutableStateOf(false) }

    TopAppBarScreen(
        topBar = {
            TitleTopAppBar(
                title = stringResource(R.string.add_haircut_tab_app_bar),
                onBack = onBack
            )
        },
    ) {
        LazyPaddingScreen(
            horizontalSpaceCount = 4,
            verticalSpaceCount = 2,
        ) {
            item {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    },
                ) {
                    TextField(
                        readOnly = true,
                        value = cutName,
                        onValueChange = { },
                        label = { Text("Label") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expanded
                            )
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        modifier = Modifier.fillMaxWidth().menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        }
                    ) {
                        cutNamesToId.keys.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(text = option) },
                                onClick = {
                                    cutName = option
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }

            item {
                DefaultSpacer()
                DatePickerDocked(
                    selectedDate = date
                )
            }

            item {
                DefaultSpacer(2)
                Button(
                    onClick = {
                        onAddCut(CutForm(name = cutName, date = date.value))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RectangleShape
                ) {
                    Text(stringResource(R.string.create_haircut))
                }
            }
        }
    }
}
