package com.example.meowmates.view.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.meowmates.view.bottombar.BottomBar
import com.example.meowmates.view.navigation.Navigation
import com.example.meowmates.view.ui.theme.MeowMatesTheme
import com.example.meowmates.view.ui.theme.ThemeMode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val controller = rememberNavController()
            val isVisibleBottomBar = remember { mutableStateOf(false) }
            MeowMatesTheme(
                currentTheme = ThemeMode.Dark
            ) {
                Scaffold (
                    modifier = Modifier.fillMaxSize().background(MeowMatesTheme.colors.background),
                    bottomBar = {
                        if(isVisibleBottomBar.value) {
                            BottomBar(controller)
                        }
                    }
                ){
                    Box(
                        modifier = Modifier.fillMaxWidth().background(MeowMatesTheme.colors.colorBar)
                    ){
                        Navigation(controller, isVisibleBottomBar)
                    }

                }
            }
        }
    }
}
