package com.example.meowmates.view.screens.splash



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.meowmates.R
import com.example.meowmates.view.navigation.NavigationRoutes
import com.example.meowmates.view.ui.theme.MeowMatesTheme
import kotlinx.coroutines.delay

@Composable
fun Splash(controller: NavHostController, viewModel: SplashViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        viewModel.launch(controller)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MeowMatesTheme.colors.background)
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Meow",
                color = Color(0xFFE0C6B5),
                fontSize = 75.sp,
                modifier = Modifier.padding(bottom = 16.dp),
                style = MeowMatesTheme.fonts.title
            )

            Text(
                text = "Mates",
                color = MeowMatesTheme.colors.title,
                fontSize = 75.sp,
                modifier = Modifier.padding(bottom = 16.dp),
                style = MeowMatesTheme.fonts.title
            )
        }

    }

}