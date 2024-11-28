package com.example.meowmates.view.screens.logIn

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
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
    

    private var _state = mutableStateOf<ResultStateSignIn>(ResultStateSignIn.Loading)
    var state: ResultStateSignIn
        get() = _state.value
        set(value) {
            _state.value = value
        }

    fun LogIn(controller: NavHostController, context: Context){
        viewModelScope.launch {
            try{
                val user = Constants.supabase.auth.signInWith(Email){
                    email = emailUser.value
                    password = passwordUser.value
                }

                currentUser = Constants.supabase.auth.currentUserOrNull()?.id
                Toast.makeText(context, "Авторизация прошла успешно", Toast.LENGTH_SHORT).show()
                Log.d("log in","Success")
                Log.d("log in", currentUser.toString())
                state = ResultStateSignIn.Success("Success")
                controller.navigate(NavigationRoutes.HOME){
                    popUpTo(NavigationRoutes.LOGIN){
                        inclusive = true
                    }
                }

            }
            catch(e:Exception){
                Log.d("log in",e.message.toString())
                Toast.makeText(context, "Возникла ошибка входа: ${e.message.toString()}", Toast.LENGTH_SHORT).show()
                state = ResultStateSignIn.Error(e.message.toString())
                //_logInState.value = ResultLogIn.Error(e.message ?: "Unknown error")
            }
        }
    }
}