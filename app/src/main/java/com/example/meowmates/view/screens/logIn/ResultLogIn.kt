package com.example.meowmates.view.screens.logIn

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.meowmates.model.database.Users

sealed class ResultStateSignIn {
    data object Loading : ResultStateSignIn()
    data class Success(val message: String) : ResultStateSignIn()
    data class Error(val message: String) : ResultStateSignIn()
}