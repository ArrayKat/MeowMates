package com.example.meowmates.view.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.meowmates.R


val Montseratt = FontFamily(
    Font(R.font.montseratt_bold, FontWeight.Bold),
    Font(R.font.montseratt_regular, FontWeight.Normal)
)
val CatFonts = FontFamily(
    Font(R.font.cat)
)

data class myType(
    val textWatermark: TextStyle,
    val title:TextStyle,
    val defaultText:TextStyle
    )

val type = myType(
    textWatermark = TextStyle(
        fontFamily = Montseratt,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    title = TextStyle(
        fontFamily = CatFonts
    ),
    defaultText = TextStyle(
        fontFamily = Montseratt,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

)
val LocalFonts = staticCompositionLocalOf { type }


