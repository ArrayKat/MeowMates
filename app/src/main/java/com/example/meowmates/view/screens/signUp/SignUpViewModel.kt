package com.example.meowmates.view.screens.signUp


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.meowmates.domain.utils.Constants
import com.example.meowmates.view.navigation.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(): ViewModel() {
    val email = mutableStateOf("")
    var password = mutableStateOf("")

    fun signUp(controller: NavHostController, passwordUser: String, emailUser: String){
        viewModelScope.launch {
            try{
                val user = Constants.supabase.auth.signInWith(Email){
                    email = emailUser
                    password = passwordUser
                }
                Log.d("sign up","Success")
                controller.navigate(NavigationRoutes.HOME){
                    popUpTo(NavigationRoutes.SIGNUP){
                        inclusive = true
                    }
                }
            }
            catch(e:Exception){
                Log.d("sign up",e.message.toString())
            }
        }
    }

}
