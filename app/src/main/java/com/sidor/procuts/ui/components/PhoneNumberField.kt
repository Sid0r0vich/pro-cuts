package com.sidor.procuts.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.sidor.procuts.R

@Composable
fun PhoneNumberField(
    phoneNumber: String?,
    onPhoneNumberChange: (String) -> Unit,
    isError: Boolean = false
) {
    val numericRegex = Regex("[^0-9]")

    MyStyledTextField(
        value = phoneNumber ?: "",
        onValueChange = {
            val stripped = numericRegex.replace(it, "")
            onPhoneNumberChange(
                if (stripped.length >= 10) {
                    stripped.substring(0..9)
                } else {
                    stripped
                }
            )
        },
        label = stringResource(R.string.client_phone_number),
        visualTransformation = PhoneVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        isError = isError
    )
}

class PhoneVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 10) text.text.substring(0..9) else text.text

        var out = if (trimmed.isNotEmpty()) "8 (" else ""

        for (i in trimmed.indices) {
            if (i == 3) out += ") "
            if (i == 6 || i == 8) out += "-"
            out += trimmed[i]
        }
        return TransformedText(AnnotatedString(out), phoneNumberOffsetTranslator)
    }

    private val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int =
            when (offset) {
                0 -> 0
                in 1..3 -> offset + 3
                in 4..6 -> offset + 5
                in 7..8 -> offset + 6
                else -> offset + 7
            }

        override fun transformedToOriginal(offset: Int): Int =
            when (offset) {
                in 0..2 -> 0
                in 3..7 -> offset - 3
                in 8..12 -> offset - 5
                in 12..13 -> offset - 6
                else -> offset - 7
            }
    }
}