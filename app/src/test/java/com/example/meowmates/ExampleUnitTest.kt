package com.example.meowmates

import com.example.meowmates.domain.utils.Constants
import com.example.meowmates.view.MainViewModel
import com.example.meowmates.view.screens.logIn.LogInViewModel
import com.example.meowmates.view.screens.logIn.ResultLogIn
import kotlinx.coroutines.Dispatchers
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private lateinit var viewModel: LogInViewModel
    private lateinit var constants: Constants




//    @Test
//    fun InitialLogInStateLoading(){
//        val viewModel = LogInViewModel()
//        assertEquals(viewModel.logInState.value, ResultLogIn.Loading)
//    }
//
//    @Test
//    fun logInStateIsSuccesfulLogIn() {
//        val viewModel = LogInViewModel()
//
//        viewModel.logInState.value = ResultLogIn.Success("Success")
//        // Проверяем, что состояние logInState было правильно установлено
//        assertEquals(viewModel.logInState.value, ResultLogIn.Success(Unit))
//    }
//
//    @Test
//    fun logInStateIsErrorLogIn() {
//        val viewModel = LogInViewModel()
//        val mockError = Exception("Invalid login credentials")
//
//        // Проверяем, что состояние logInState было правильно установлено
//        assertEquals(viewModel.logInState.value, ResultLogIn.Error(mockError.message.toString()))
//    }
}