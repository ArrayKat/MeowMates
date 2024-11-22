package com.example.meowmates.view.screens.profile.people

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.meowmates.domain.utils.Constants
import com.example.meowmates.domain.utils.PrefManager.currentUser
import com.example.meowmates.model.database.Cats
import com.example.meowmates.model.database.Users
import com.example.meowmates.view.navigation.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyProfileViewModel @Inject constructor(): ViewModel() {
    val surname= mutableStateOf("")
    val name = mutableStateOf("")
    val patro= mutableStateOf("")
    val telephone = mutableStateOf("")
    val birthdate = mutableStateOf("")
    val image_url = mutableStateOf("")

    private val _user = MutableStateFlow<Users?>(null)
    val user: StateFlow<Users?> = _user.asStateFlow()
    fun GetUser(){
        try{
            viewModelScope.launch {
                val user = Constants.supabase.from("users").select{ filter { eq("id",currentUser.toString()) } }.decodeSingle<Users>()
                surname.value = user.surname
                name.value = user.name
                patro.value = user.patronymic
                telephone.value = user.telephone ?: ""
                birthdate.value = user.birthday ?: ""
                image_url.value = user.image_url ?: ""
            }
            Log.d("MyProfileVM", "Пользовательская картинка: "+ image_url )
        }
        catch (e:Exception){
            Log.d("MyProfileVM", "Возникла ошибка загрузки пользователя: "+ e.message.toString())
        }
    }
    fun saveChanges(controller: NavHostController) {
        viewModelScope.launch {
            try {
                val user = Constants.supabase.from("users").select{ filter { eq("id",currentUser.toString()) } }.decodeSingle<Users>()
                val userToUpdate = Users(
                    id = user.id,
                    surname = surname.value,
                    name = name.value,
                    patronymic = patro.value,
                    birthday = birthdate.value,
                    image_url = user.image_url,
                    telephone = telephone.value
                )

                val result = Constants.supabase.from("users").update(userToUpdate){filter { eq("id", currentUser.toString()) }}
                Log.e("MyProfileVM", "Update data success")
                controller.navigate(NavigationRoutes.MAINPROFILE){
                    popUpTo(NavigationRoutes.MYPROFILE){
                        inclusive = true
                    }
                }
            } catch (e: Exception) {
                Log.e("MyProfileVM", "Exception saving user: ${e.message}")
                // Consider showing an error message to the user
            }
        }
    }

}