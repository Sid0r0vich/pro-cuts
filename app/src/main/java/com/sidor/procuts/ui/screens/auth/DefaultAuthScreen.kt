package com.sidor.procuts.ui.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.sidor.procuts.ui.PaddingSpaces
import com.sidor.procuts.ui.screens.PaddingProviderScreen
import com.sidor.procuts.ui.screens.PaddingScreenWithBottomButtons
import com.sidor.procuts.ui.theme.LocalColorPalette

@Composable
fun DefaultAuthScreen(
    bottomButtonText: String,
    onBottomButtonClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    PaddingProviderScreen {
        PaddingScreenWithBottomButtons(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            paddingSpaces = PaddingSpaces(2),
            buttons = {
                Button(
                    onClick = onBottomButtonClick,
                    shape = RectangleShape,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = true,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LocalColorPalette.current.buttonColor
                    )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = bottomButtonText)
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = null,
                            modifier = Modifier.padding(start = 4.dp),
                        )
                    }
                }
            }
        ) {
            content()
        }
    }
}