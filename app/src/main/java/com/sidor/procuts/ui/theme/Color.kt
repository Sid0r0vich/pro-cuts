package com.sidor.procuts.ui.theme

import androidx.compose.ui.graphics.Color

data class ColorPalette(
    val mainColor: Color,
    val oppositeColor: Color,
    val buttonColor: Color,
    val barColor: Color,
    val cardColor: Color,
    val darkFontColor: Color,
    val disabledColor: Color,
    val errorColor: Color
)

val barDarkColor = Color(0xff242f3d)
val barLightColor = Color(0xff2c3135)
val backgroundDarkColor = Color(0xff0e1621)
val backgroundLightColor = Color(0xffedeef0)
val cardDarkColor = Color(0xff3e546a)
val cardLightColor = Color(0xffffffff)
val buttonLightColor = Color(0xff2c3135)
val buttonDarkColor = Color(0xffffffff)
val disabledDarkColor = Color(0xffe6e6e6)
val disabledLightColor = Color(0xffe6e6e6)

val lightFontColor = Color.White
val darkFontColor = Color.Black

val baseLightPalette = ColorPalette(
    mainColor = Color.White,
    oppositeColor = Color.Black,
    buttonColor = buttonLightColor,
    barColor = barLightColor,
    cardColor = cardLightColor,
    darkFontColor = darkFontColor,
    disabledColor = disabledLightColor,
    errorColor = Color.Red
)
val baseDarkPalette = baseLightPalette.copy(
    mainColor = Color.Black,
    oppositeColor = Color.White,
    buttonColor = buttonDarkColor,
    barColor = barDarkColor,
    cardColor = cardDarkColor,
    darkFontColor = darkFontColor,
    disabledColor = disabledDarkColor
)
