package com.example.meowmates.view.screens.profile.main

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meowmates.domain.utils.Constants
import com.example.meowmates.domain.utils.PrefManager.currentUser
import com.example.meowmates.model.customModel.CustomCats
import com.example.meowmates.model.database.Breeds
import com.example.meowmates.model.database.Cats
import com.example.meowmates.model.database.UserCats
import com.example.meowmates.model.database.Users
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor():ViewModel() {
    var User = mutableStateOf<Users?>(null)
    var catList = mutableStateOf<List<CustomCats>>(emptyList())
    fun GetData(){
        viewModelScope.launch {
            try{
                val fetchUser = Constants.supabase.from("users").select {
                    filter {
                        eq("id", currentUser.toString())
                    }
                }.decodeSingle<Users>()
                User.value = fetchUser
                Log.d("ProfileVM", "Загружен пользователь: "+ User.value.toString())
            } catch (e: Exception) {
                Log.d("ProfileVM", "Ошибка загрузки пользователя: ${e.message}")
            }
            try{
                val idCats = Constants.supabase.from("user_cats").select {
                    filter {
                        eq("id_user", currentUser.toString())
                    }
                }.decodeList<UserCats>()
                val result = idCats.map { it ->
                    val cat = Constants.supabase.from("cats").select {filter {eq("id", it.id_cats)}}.decodeSingle<Cats>()
                    val breedCat = Constants.supabase.from("breeds").select { filter { eq("id", cat.breed_id) } }.decodeSingle<Breeds>()
                    CustomCats(
                        id = it.id,
                        name_cat = cat.name_cat,
                        gender_id = cat.gender_id,
                        age = cat.age,
                        breed_id = cat.breed_id,
                        weight = cat.weight,
                        description_cats = cat.description_cats,
                        image_url = cat.image_url,
                        breed_name = breedCat.breed,
                    )
                }
                catList.value = result
                Log.d("ProfileVM", "Коты пользователя: ${catList.value}")
            }catch (e: Exception) {
                Log.d("ProfileVM", "Ошибка загрузки котов пользователя: ${e.message}")
            }
        }

    }

}