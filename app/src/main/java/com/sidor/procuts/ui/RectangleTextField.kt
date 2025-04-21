package com.sidor.procuts.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.RectangleShape
import com.sidor.procuts.ui.theme.LocalColorPalette

@Composable
fun RectangleTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier.fillMaxWidth(),
        shape = RectangleShape,
        enabled = enabled,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = LocalColorPalette.current.darkFontColor,
            focusedContainerColor = LocalColorPalette.current.mainColor,
            unfocusedContainerColor = LocalColorPalette.current.mainColor,
            disabledContainerColor = LocalColorPalette.current.disabledColor
        ),
    )
}