package com.example.meowmates.view.screens.logIn

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.meowmates.domain.utils.Constants
import com.example.meowmates.domain.utils.PrefManager.currentUser
import com.example.meowmates.view.navigation.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(): ViewModel(){
    val emailUser = mutableStateOf("")
    val passwordUser = mutableStateOf("")


    fun LogIn(controller: NavHostController){
        viewModelScope.launch {
            try{
                val user = Constants.supabase.auth.signInWith(Email){
                    email = emailUser.value
                    password = passwordUser.value
                }
                //currentUser = Constants.supabase.auth.currentUserOrNull()?.id

                val result = Constants.supabase.auth.currentUserOrNull()
                currentUser = Constants.supabase.auth.currentUserOrNull()?.id

                Log.d("log in","Success")
                Log.d("log in", currentUser.toString())
                controller.navigate(NavigationRoutes.HOME){
                    popUpTo(NavigationRoutes.LOGIN){
                        inclusive = true
                    }
                }
                //_logInState = ResultLogIn.
            }
            catch(e:Exception){
                Log.d("log in",e.message.toString())
                //_logInState.value = ResultLogIn.Error(e.message ?: "Unknown error")
            }
        }
    }
}