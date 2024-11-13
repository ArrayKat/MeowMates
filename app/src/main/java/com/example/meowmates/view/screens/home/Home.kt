package com.example.meowmates.view.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.meowmates.view.ui.theme.MeowMatesTheme

@Composable
fun Home (navHostController: NavHostController) {
    Box(
        modifier = Modifier
            .background(MeowMatesTheme.colors.background)
    ){}
}