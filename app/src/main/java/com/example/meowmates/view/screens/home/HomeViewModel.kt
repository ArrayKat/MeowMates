package com.example.meowmates.view.screens.home

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.meowmates.domain.utils.Constants
import com.example.meowmates.domain.utils.PrefManager.currentUser
import com.example.meowmates.model.customModel.CustomCats
import com.example.meowmates.model.database.Breeds
import com.example.meowmates.model.database.Cats
import com.example.meowmates.model.database.UserFavoriteCat
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.realtime.PostgresAction
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresChangeFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor():ViewModel() {
    var catsList = mutableStateOf<List<CustomCats>>(emptyList())
    var breedsList = mutableStateOf<List<Breeds>>(emptyList())
    var favoriteCatList = mutableStateOf<List<UserFavoriteCat>>(emptyList())

    init{
        GetComponent()
    }
    fun GetComponent(){
        GetFavoriteCat()
        GetBreeds()
        GetCats()
    }
    private fun GetFavoriteCat(){
        viewModelScope.launch {
            try{
                val responce = Constants.supabase.from("user_favorite_cats").select {
                    filter {
                        eq("id_user", currentUser.toString())
                    }
                }.decodeList<UserFavoriteCat>()
                favoriteCatList.value = responce
                Log.d("HomeVM-GetFavoriteCat", favoriteCatList.value.toString())
            }
            catch (e:Exception){
                Log.d("HomeVM-GetFavoriteCat", "Ошибка загрузки любимых котов: ${e.message}")
            }
        }
    }
    private fun GetBreeds() {
        viewModelScope.launch {
            try {
                val response = Constants.supabase.from("breeds").select().decodeList<Breeds>()
                breedsList.value = response
            } catch (e: Exception) {
                Log.d("HomeVM", "Ошибка загрузки пород: ${e.message}")
            }
        }
    }
    private fun GetCats(){
        viewModelScope.launch {
            try{
                val response = Constants.supabase.from("cats").select().decodeList<Cats>()
                if(response.isEmpty()){
                    Log.d("HomeVM-GetCats", "У вас проблемы, котики пустые")
                }

                // Сопоставляем данные о котах с названиями пород
                catsList.value = response.map { cat ->
                    val breed = breedsList.value.find { it.id == cat.breed_id }

                    val favorite = favoriteCatList.value.find { it.id_cat == cat.id }
                    var boolFavCat: Boolean = false
                    if( favorite?.id_cat == cat.id) boolFavCat = true

                    CustomCats(
                        id = cat.id,
                        name_cat = cat.name_cat,
                        gender_id = cat.gender_id,
                        age = cat.age,
                        breed_id = cat.breed_id,
                        weight = cat.weight,
                        description_cats = cat.description_cats,
                        image_url = cat.image_url,
                        breed_name = breed?.breed ?: "Неизвестная порода", // Название породы
                        favorite = boolFavCat
                    )
                }

            }
            catch (e: Exception){
                Log.d("HomeView-GetCats", e.message.toString())
            }
        }
    }
    fun realtimeHome(scope: CoroutineScope){
        viewModelScope.launch {
            try{
                val channel = Constants.supabase.channel("user_favorite_cats")
                val dataFlow = channel.postgresChangeFlow<PostgresAction>(schema = "public")

                dataFlow.onEach {
                    when(it){
                        is PostgresAction.Delete -> {
                            val stringData = it.oldRecord.toString()
                            Log.d("HomeView-realtime", "Удаленные данные: "+stringData)
                            val data = Json.decodeFromString<UserFavoriteCat>(stringData)
                            GetComponent()
                        }
                        is PostgresAction.Insert -> {
                            val stringData = it.record.toString()
                            Log.d("HomeView-realtime", "Добавленные данные: "+stringData)
                            val data = Json.decodeFromString<UserFavoriteCat>(stringData)
                            GetComponent()
                        }
                        is PostgresAction.Select -> TODO()
                        is PostgresAction.Update -> TODO()
                    }
                }
            }
            catch (e:Exception){
                Log.d("HomeVM - realtime", "ERROR")
            }
        }

    }

}