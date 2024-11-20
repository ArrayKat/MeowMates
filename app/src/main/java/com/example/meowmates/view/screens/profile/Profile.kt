package com.example.meowmates.view.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.meowmates.view.ui.theme.MeowMatesTheme

@Composable
fun Profile (navHostController: NavHostController){
    Box(
        modifier = Modifier
            .background(MeowMatesTheme.colors.background)
            .fillMaxSize()
    ){
        Text(
            text = "Profile Screen",
            fontSize = 20.sp,
            color = MeowMatesTheme.colors.text,
            modifier = Modifier
                .padding(20.dp)

        )
    }
}