package com.example.meowmates

import com.example.meowmates.domain.utils.Constants
import com.example.meowmates.view.screens.logIn.LogInViewModel
import com.example.meowmates.view.screens.logIn.ResultStateSignIn
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private lateinit var viewModel: LogInViewModel
    private lateinit var constants: Constants

    @Test
    fun InitialLogInStateLoading(){
        val viewModel = LogInViewModel()
        assertEquals(viewModel.state, ResultStateSignIn.Loading)
    }

    @Test
    fun logInStateIsSuccesfulLogIn() {
        val viewModel = LogInViewModel()

        viewModel.state = ResultStateSignIn.Success("Success")
        // Проверяем, что состояние logInState было правильно установлено
        assertEquals(viewModel.state, ResultStateSignIn.Success("Success"))
    }

    @Test
    fun logInStateIsErrorLogIn() {
        val viewModel = LogInViewModel()
        viewModel.state = ResultStateSignIn.Error("Invalid login credentials")

        // Проверяем, что состояние logInState было правильно установлено
        assertEquals(viewModel.state, ResultStateSignIn.Error("Invalid login credentials"))
    }
}