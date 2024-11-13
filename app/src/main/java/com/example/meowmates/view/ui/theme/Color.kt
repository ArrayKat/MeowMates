package com.example.meowmates.view.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CollorPalete(
    val background: Color,
    val title:Color,
    val text: Color,
    val inversionText: Color,
    val activIcon:Color,
    val passivIcon:Color,
    val colorBar: Color,
    val container: Color,
    val button:Color
)

//светлая тема - не перенесены цвета
val ColorBar2 = Color(0xFFFFFFFF)
val Background2 = Color(0xFFF9EFE0)
val Primary2 = Color(0xFFF2DEC0)
val Secondary2 = Color(0xFF98684D)
val Tertiary2 = Color(0xFF553D39)

val baseLightPalette = CollorPalete(
    background = Background2,
    title = Tertiary2,
    text= Tertiary2,
    inversionText = Background2,
    activIcon =  Secondary2,
    passivIcon =Tertiary2,
    colorBar = ColorBar2,
    container = Primary2,
    button = Secondary2
)
//темная тема
val Primary1 = Color(0xFFE0C6B5)
val Secondary1 = Color(0xFF4A3D30)
val Background1 = Color(0xFF2B2B2B)
val ColorBar1 = Color(0xFF000000)

val baseDarkPalette = CollorPalete(
    background = Background1,
    title = Primary1,
    text= Primary1,
    inversionText = Background1,
    activIcon =  Primary1,
    passivIcon = Secondary1,
    colorBar = ColorBar1,
    container = Secondary1,
    button = Primary1
)
val LocalColors= staticCompositionLocalOf<CollorPalete>{ baseDarkPalette }
