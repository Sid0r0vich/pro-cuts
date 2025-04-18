package com.sidor.procuts.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CutQuestionnaireSecondScreen(
    onBack: () -> Unit,
    onNext: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBarScreen(
        topBar = {
            TitleTopAppBar(
                title = stringResource(R.string.add_haircut_tab_app_bar),
                onBack = onBack
            )
        },
    ) {
        DefaultPaddingScreenWithQuestionnaireButtons(
            onNext = onNext,
            onBack = onBack,
            horizontalSpaceCount = 2,
            verticalSpaceCount = 2,
        ) {
//            ExposedDropdownMenuBox(
//                expanded = expanded,
//                onExpandedChange = {
//                    expanded = !expanded
//                },
//            ) {
//                TextField(
//                    readOnly = true,
//                    value = cutName,
//                    onValueChange = { },
//                    label = { Text(text = stringResource(R.string.cut_name)) },
//                    trailingIcon = {
//                        ExposedDropdownMenuDefaults.TrailingIcon(
//                            expanded = expanded
//                        )
//                    },
//                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
//                    modifier = Modifier.fillMaxWidth().menuAnchor()
//                )
//                ExposedDropdownMenu(
//                    expanded = expanded,
//                    onDismissRequest = {
//                        expanded = false
//                    }
//                ) {
//                    cutNamesToId.keys.sorted().forEach { option ->
//                        DropdownMenuItem(
//                            text = { Text(text = option) },
//                            onClick = {
//                                cutName = option
//                                expanded = false
//                            }
//                        )
//                    }
//                }
//            }
        }
    }
}

