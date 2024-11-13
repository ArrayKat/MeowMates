package com.example.meowmates.view.screens.signUp


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(): ViewModel() {
    val email = mutableStateOf("")
    var password = mutableStateOf("")

    fun signUp(controller: NavHostController){

    }

}
