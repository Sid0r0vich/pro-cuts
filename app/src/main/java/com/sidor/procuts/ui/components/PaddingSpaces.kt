package com.sidor.procuts.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp

class PaddingSpaces(
    val start: Int = 0,
    val top: Int = 0,
    val end: Int = 0,
    val bottom: Int = 0
) {
    constructor(
        horizontal: Int = 0,
        top: Int = 0,
        bottom: Int = 0
    ) : this(start = horizontal, top = top, end = horizontal, bottom = bottom)

    constructor(all: Int) : this(start = all, top = all, end = all, bottom = all)

    constructor(
        horizontal: Int = 0,
        vertical: Int = 0
    ) : this(start = horizontal, top = vertical, end = horizontal, bottom = vertical)

    fun toPaddingValues(spaceSize: Dp): PaddingValues {
        return PaddingValues(
            start = spaceSize * start,
            top = spaceSize * top,
            end = spaceSize * end,
            bottom = spaceSize * bottom
        )
    }
}