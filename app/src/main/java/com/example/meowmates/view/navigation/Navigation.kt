package com.example.meowmates.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.meowmates.view.screens.favorites.Favorites
import com.example.meowmates.view.screens.home.Home
import com.example.meowmates.view.screens.logIn.LogIn
import com.example.meowmates.view.screens.message.Message
import com.example.meowmates.view.screens.profile.main.Profile
import com.example.meowmates.view.screens.signUp.SignUp
import com.example.meowmates.view.screens.splash.Splash

@Composable
fun Navigation(controller: NavHostController, isVisibleBar: MutableState<Boolean>) {
    NavHost(
        navController = controller,
        startDestination = NavigationRoutes.LOGIN
    ) {
        composable(NavigationRoutes.SPLASH) {
            isVisibleBar.value = false
            Splash(controller)
        }
        composable(NavigationRoutes.SIGNUP) {
            isVisibleBar.value = false
            SignUp(controller)
        }
        composable(NavigationRoutes.LOGIN) {
            isVisibleBar.value = false
            LogIn(controller)
        }
        composable(NavigationRoutes.PROFILE) {
            isVisibleBar.value = true
            Profile(controller)
        }
        composable(NavigationRoutes.HOME) {
            isVisibleBar.value = true
            Home(controller)
        }
        composable(NavigationRoutes.FAVORITES) {
            isVisibleBar.value = true
            Favorites(controller)
        }
        composable(NavigationRoutes.MESSAGE) {
            isVisibleBar.value = true
            Message(controller)
        }
    }
}