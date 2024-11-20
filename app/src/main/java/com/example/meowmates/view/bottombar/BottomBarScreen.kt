package com.example.meowmates.view.bottombar

import com.example.meowmates.R
import com.example.meowmates.view.navigation.NavigationRoutes

sealed class BottomBarScreen(
    val route: String,
    val icon: Int? = null
){

    object Profile:BottomBarScreen(
        route = NavigationRoutes.PROFILE,
        icon = R.drawable.profile_icon
    )
    object Home:BottomBarScreen(
        route = NavigationRoutes.HOME,
        icon = R.drawable.home_icon
    )
    object Message:BottomBarScreen(
        route = NavigationRoutes.MESSAGE,
        icon = R.drawable.message_icon
    )
    object Favorites:BottomBarScreen(
        route = NavigationRoutes.FAVORITES,
        icon = R.drawable.favorites_icon
    )

}

val ScreenItems = listOf(BottomBarScreen.Home,BottomBarScreen.Message,BottomBarScreen.Favorites, BottomBarScreen.Profile)