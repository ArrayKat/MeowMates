package com.example.meowmates.view.screens.signUp


sealed class ResultSignUp {
    data object Loading : ResultSignUp()
    data class Success(val message: String) : ResultSignUp()
    data class Error(val message: String) : ResultSignUp()
}