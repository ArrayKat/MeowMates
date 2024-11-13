package com.example.meowmates.view.ui.theme

import android.app.Activity
import android.hardware.lights.Light
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import java.lang.reflect.Type

object MeowMatesTheme {
    val colors: CollorPalete
        @ReadOnlyComposable
        @Composable
        get() = LocalColors.current

    val fonts: myType
        @ReadOnlyComposable
        @Composable
        get() = LocalFonts.current
}

@Composable
fun MeowMatesTheme(
    currentTheme: Int = ThemeMode.Dark,
    fonts: myType = MeowMatesTheme.fonts,
    content : @Composable () -> Unit
) {

    val colors = when (currentTheme){
        ThemeMode.Light -> baseLightPalette
        ThemeMode.Dark -> baseDarkPalette
        else -> if(isSystemInDarkTheme()){
            baseDarkPalette
        } else {
            baseLightPalette
        }
    }
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalFonts provides fonts,
        content = content
    )
}

data object ThemeMode {
    const val System = 0
    const val Light = 1
    const val Dark = 2
}
