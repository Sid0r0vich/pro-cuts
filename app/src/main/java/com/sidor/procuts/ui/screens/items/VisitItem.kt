package com.sidor.procuts.ui.screens.items


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sidor.procuts.data.readableDMYDateFormat
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun VisitItem(
    modifier: Modifier = Modifier,
    date: Date,
    onClick: () -> Unit = {}
) {
    DefaultItem(
        modifier = modifier,
        text = readableDMYDateFormat.format(date),
        onClick = onClick
    )
}