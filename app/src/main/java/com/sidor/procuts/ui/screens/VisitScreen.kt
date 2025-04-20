package com.sidor.procuts.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R
import com.sidor.procuts.data.Cut
import com.sidor.procuts.data.CutDate
import com.sidor.procuts.data.allCuts
import com.sidor.procuts.data.nameToLabelId
import com.sidor.procuts.data.readableDMYDateFormat
import com.sidor.procuts.ui.PaddingSpaces
import com.sidor.procuts.ui.TextWithBoldField
import com.sidor.procuts.ui.screens.items.CutItem
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitScreen(
    visit: CutDate,
    onBack: () -> Unit,
    onCutClick: (Cut) -> Unit,
    cutParams: Map<String, String>
) {
    TopAppBarScreen(
        topBar = {
            TitleTopAppBar(
                title = stringResource(R.string.visit_tab_app_bar),
                onBack = onBack
            )
        },
    ) {
        LazyPaddingScreen(
            paddingSpaces = PaddingSpaces(2)
        ) {
            nameToLabelId.forEach { (name, label) ->
                item {
                    if (cutParams[name] != null) {
                        TextWithBoldField(
                            field = stringResource(label),
                            value = cutParams[name]!!,
                            style = MaterialTheme.typography.titleLarge
                        )
                        DefaultSpacer()
                    }
                }
            }
            item {
                TextWithBoldField(
                    field = stringResource(R.string.date_field),
                    value = readableDMYDateFormat.format(visit.date),
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            item {
                val cut = allCuts[visit.cutId]
                DefaultSpacer()
                CutItem(
                    name = cut?.name ?: stringResource(R.string.no_found_cut_name),
                    onClick = { cut?.let { onCutClick(it) } }
                )
            }
        }
    }
}