package com.example.meowmates

import com.example.meowmates.domain.utils.Constants
import com.example.meowmates.view.screens.logIn.LogInViewModel
import com.example.meowmates.view.screens.logIn.ResultStateSignIn
import com.example.meowmates.view.screens.signUp.ResultSignUp
import com.example.meowmates.view.screens.signUp.SignUpViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private lateinit var LogInVM: LogInViewModel
    private lateinit var SignUpVM: SignUpViewModel

    @Test
    fun logInState_Is_InitialState(){
        val viewModel = LogInViewModel()
        assertEquals(viewModel.state, ResultStateSignIn.Loading)
    }
    @Test
    fun logInState_Is_SuccessLogIn() {
        val viewModel = LogInViewModel()
        viewModel.state = ResultStateSignIn.Success("Success")
        // Проверяем, что состояние logInState было правильно установлено
        assertEquals(viewModel.state, ResultStateSignIn.Success("Success"))
    }
    @Test
    fun logInState_Is_ErrorLogIn() {
        val viewModel = LogInViewModel()
        viewModel.state = ResultStateSignIn.Error("Invalid login credentials")
        // Проверяем, что состояние logInState было правильно установлено
        assertEquals(viewModel.state, ResultStateSignIn.Error("Invalid login credentials"))
    }
    @Test
    fun signUpState_Is_InitialState(){
        val viewModel = SignUpViewModel()
        assertEquals(viewModel.state, ResultSignUp.Loading)
    }
    @Test
    fun signUpState_Is_SuccessSignUp() {
        val viewModel = SignUpViewModel()
        viewModel.state = ResultSignUp.Success("Success")
        // Проверяем, что состояние logInState было правильно установлено
        assertEquals(viewModel.state, ResultSignUp.Success("Success"))
    }
}