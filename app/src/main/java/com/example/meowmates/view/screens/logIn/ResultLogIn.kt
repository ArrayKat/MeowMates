package com.example.meowmates.view.screens.logIn

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.meowmates.model.database.Users

sealed class ResultLogIn {
    var Loading: MutableState<Boolean> = mutableStateOf(true)
    var Success: MutableState<String> = mutableStateOf ("")
    var Error: MutableState<String> = mutableStateOf ("")
}