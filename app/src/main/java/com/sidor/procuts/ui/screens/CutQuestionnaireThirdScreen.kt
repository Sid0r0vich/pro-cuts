package com.sidor.procuts.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CutQuestionnaireThirdScreen(
    onBack: () -> Unit,
    onAddCut: () -> Unit
) {
    TopAppBarScreen(
        topBar = {
            TitleTopAppBar(
                title = stringResource(R.string.add_haircut_tab_app_bar),
                onBack = onBack
            )
        },
    ) {
        PaddingScreen(
            horizontalSpaceCount = 4,
            verticalSpaceCount = 2,
        ) {
            DefaultSpacer(2)
            Button(
                onClick = onAddCut,
                modifier = Modifier.fillMaxWidth(),
                shape = RectangleShape
            ) {
                Text(stringResource(R.string.create_haircut))
            }
        }
    }
}
