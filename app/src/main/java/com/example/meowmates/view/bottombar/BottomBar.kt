package com.example.meowmates.view.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.meowmates.view.ui.theme.MeowMatesTheme

@Composable
fun BottomBar(controller: NavHostController){
    Box(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .background(MeowMatesTheme.colors.colorBar)
            .padding(bottom = 40.dp),
        contentAlignment = Alignment.Center
    ){
        val navBackStackEntry by controller.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceAround

        ){

            ScreenItems.forEach { screen ->
                var selectedColor = MeowMatesTheme.colors.passivIcon
                if(currentRoute==screen.route){
                    selectedColor = MeowMatesTheme.colors.activIcon
                }
                    Icon(
                        imageVector = ImageVector.vectorResource(id = screen.icon!!),
                        contentDescription = "",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { if(currentRoute!=screen.route){
                                controller.navigate(screen.route){
                                    currentRoute?.let{
                                        popUpTo(it){
                                            inclusive = true
                                        }
                                    }
                                }
                            } },
                        tint = selectedColor,

                    )


            }
        }
    }
}

