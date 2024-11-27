package com.example.meowmates.view.screens.signUp


import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.meowmates.domain.utils.Constants
import com.example.meowmates.domain.utils.PrefManager.currentUser
import com.example.meowmates.model.database.Users
import com.example.meowmates.view.navigation.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(): ViewModel() {
    val surname= mutableStateOf("")
    val name = mutableStateOf("")
    val patro= mutableStateOf("")
    val telephone = mutableStateOf("")
    val birthdate = mutableStateOf("")
    val emailUser = mutableStateOf("")
    var passwordUser = mutableStateOf("")

    fun signUp(controller: NavHostController, context: Context){
        if(surname.value!="" && name.value!=""
            && patro.value!="" && telephone.value!=""
            && emailUser.value!="" && passwordUser.value!="" && birthdate.value!=""
            ) {
            viewModelScope.launch {
                try {
                    Constants.supabase.auth.signUpWith(Email) {
                        email = emailUser.value
                        password = passwordUser.value
                    }
                    val user = Constants.supabase.auth.currentUserOrNull()
                        if(user != null) {
                        Log.d("sign up", "Был зарегистрирован следующий пользователь: ${user.id}")
                        Log.d("sign up", "Пользователь: ${Constants.supabase.auth.currentUserOrNull()!!.id}")

                        val newUser = Users(
                            id = user.id,
                            surname = surname.value,
                            name = name.value,
                            patronymic = patro.value,
                            birthday = birthdate.value,
                            image_url = "",
                            telephone = telephone.value
                        )
                        var result = Constants.supabase.from("users").insert(newUser)
                        currentUser = Constants.supabase.auth.currentUserOrNull()?.id
                        Log.d("sign up","Success")
                        Log.d("sign up", currentUser.toString())
                        Toast.makeText(context, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show()
                        controller.navigate(NavigationRoutes.HOME){
                            popUpTo(NavigationRoutes.SIGNUP){
                                inclusive = true
                            }
                        }

                    }
                } catch (e: Exception) {
                    Log.d("sign up", "ERROR: ${e.message.toString()}")
                    Toast.makeText(context, "Произошла ошибка: ${e.message.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else{
            //Toast.makeText(context, "Не все поля заполненны", Toast.LENGTH_SHORT).show()
            Log.d("sign up", "Не все поля заполненны")
            Toast.makeText(context, "Не все поля заполненны", Toast.LENGTH_SHORT).show()
        }

    }
}
