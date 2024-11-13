package com.example.meowmates.view.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(controller: NavHostController){
    val screens = listOf(BottomBarRoutes.HomeScreen, BottomBarRoutes.MessageScreen, BottomBarRoutes.FavoriteScreen, BottomBarRoutes.ProfileScreen)
    Box{
        val navBackStackEntry by controller.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Row(
            modifier = Modifier
                .background(Color.Black)
                .padding(bottom = 12.dp, top = 8.dp)
        ){
            screens.forEach { screen ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {  }
                ) {
                    if(currentRoute!=screen.route){
                        controller.navigate(screen.route){
                            currentRoute?.let{
                                popUpTo(it){
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}

