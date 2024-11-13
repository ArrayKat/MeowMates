package com.example.meowmates.view.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.meowmates.view.navigation.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(): ViewModel() {

    fun launch(controller: NavHostController){
        viewModelScope.launch {
            delay(2000L)
            controller.navigate(NavigationRoutes.LOGIN) {
                popUpTo(NavigationRoutes.SPLASH){
                    inclusive = true;
                }
            }
        }
    }

}