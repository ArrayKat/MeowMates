package com.example.meowmates.view.screens.profile.main

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.meowmates.domain.utils.Constants
import com.example.meowmates.domain.utils.PrefManager.currentUser
import com.example.meowmates.model.customModel.CustomCats
import com.example.meowmates.model.database.Breeds
import com.example.meowmates.model.database.Cats
import com.example.meowmates.model.database.UserCats
import com.example.meowmates.model.database.Users
import com.example.meowmates.view.converter.bitmapToByteArray
import com.example.meowmates.view.navigation.NavigationRoutes
import com.example.meowmates.view.screens.profile.cat.CatProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainProfileViewModel @Inject constructor():ViewModel() {
    var User = mutableStateOf<Users?>(null)
    var catList = mutableStateOf<List<CustomCats>>(emptyList())

    fun navigateToMyProfile(controller:NavHostController){
        controller.navigate(NavigationRoutes.MYPROFILE) {
            popUpTo(NavigationRoutes.MAINPROFILE){
                inclusive = true;
            }
        }
    }
    fun navigateToLogIn(controller:NavHostController){
        controller.navigate(NavigationRoutes.LOGIN) {
            popUpTo(NavigationRoutes.MAINPROFILE){
                inclusive = true;
            }
        }
    }
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
                        id = cat.id,
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
    fun addPictureSupa(fileName:String,bucketName:String, byteArr:ByteArray){
        viewModelScope.launch {
            try{
                val bucket = Constants.supabase.storage[bucketName]
                bucket.upload("${fileName}.jpg", byteArr){ upsert = true}
                Log.d("MainProfileVM", "Файл загружен!!!")
            }
            catch (e: Exception){
                Log.d("MainProfileVM", "ОШИБКА: что то не так с загрузкой файла в supabase: ${e.message.toString()}")
            }
        }
    }
    fun createBucket(name: String) {
        viewModelScope.launch {
            try {
               Constants.supabase.storage.createBucket(id = name){
                    public = false
                    fileSizeLimit = 10.megabytes
               }
                Log.d("MainProfileVM", "Корзина создалась")
            } catch(e: Exception) {
                Log.d("MainProfileVM", "ОШИБКА не создалась корзина: ${e.message.toString()}")
            }
        }
    }


}