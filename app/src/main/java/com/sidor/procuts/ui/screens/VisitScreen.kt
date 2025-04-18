package com.sidor.procuts.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.sidor.procuts.R
import com.sidor.procuts.data.Cut
import com.sidor.procuts.data.CutDate
import com.sidor.procuts.data.allCuts
import com.sidor.procuts.data.DMYDateFormat
import com.sidor.procuts.data.readableDMYDateFormat
import com.sidor.procuts.ui.TextWithBoldField
import com.sidor.procuts.ui.screens.items.CutItem
import com.sidor.procuts.ui.screens.items.VisitItem
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitScreen(
    visit: CutDate,
    onBack: () -> Unit,
    onCutClick: (Cut) -> Unit
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
            horizontalSpaceCount = 2,
            verticalSpaceCount = 2,
        ) {
            item {
                Column {
                    TextWithBoldField(
                        field = stringResource(R.string.date_field),
                        value = readableDMYDateFormat.format(visit.date),
                        style = MaterialTheme.typography.titleLarge,
                    )
                    DefaultSpacer(1)
                }
            }
            item {
                val cut = allCuts[visit.cutId]
                CutItem(
                    name = cut?.name ?: stringResource(R.string.no_found_cut_name),
                    onClick = { cut?.let { onCutClick(it) } }
                )
            }
        }
    }
}