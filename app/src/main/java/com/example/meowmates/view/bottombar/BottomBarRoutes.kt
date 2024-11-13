package com.example.meowmates.view.bottombar

import com.example.meowmates.R
import com.example.meowmates.view.navigation.NavigationRoutes

sealed class BottomBarRoutes(
    val route: String,
    val title: String ="",
    val resourceId: Int? = null
){
    object HomeScreen : BottomBarRoutes(
        route = NavigationRoutes.HOME,
        title = "Главная",
        resourceId = R.drawable.home_icon
    )
    object MessageScreen : BottomBarRoutes(
        route = NavigationRoutes.MESSAGE,
        title = "Сообщения",
        resourceId = R.drawable.home_icon
    )
    object FavoriteScreen : BottomBarRoutes(
        route = NavigationRoutes.FAVORITES,
        title = "Избранное",
        resourceId = R.drawable.home_icon
    )
    object ProfileScreen : BottomBarRoutes(
        route = NavigationRoutes.PROFILE,
        title = "Профиль",
        resourceId = R.drawable.home_icon
    )
}