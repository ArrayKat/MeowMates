package com.example.meowmates.view.screens.favorites

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meowmates.domain.utils.Constants
import com.example.meowmates.domain.utils.PrefManager.currentUser
import com.example.meowmates.model.customModel.CustomCats
import com.example.meowmates.model.database.Breeds
import com.example.meowmates.model.database.Cats
import com.example.meowmates.model.database.UserFavoriteCat
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoritesViewModel @Inject constructor(): ViewModel() {
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
                Log.d("FavoritesVM-GetFavoriteCat", favoriteCatList.value.toString())
            }
            catch (e:Exception){
                Log.d("FavoritesVM-GetFavoriteCat", "Ошибка загрузки любимых котов: ${e.message}")
            }
        }
    }
    private fun GetBreeds() {
        viewModelScope.launch {
            try {
                val response = Constants.supabase.from("breeds").select().decodeList<Breeds>()
                breedsList.value = response
            } catch (e: Exception) {
                Log.d("FavoritesVM", "Ошибка загрузки пород: ${e.message}")
            }
        }
    }
    private fun GetCats(){
        viewModelScope.launch {
            try{
                val cats = Constants.supabase.from("cats").select().decodeList<Cats>()
                catsList.value = favoriteCatList.value.map{ items->
                    val cat = cats.find { it.id == items.id_cat }
                    val breed = breedsList.value.find { it.id == cat?.breed_id }
                    CustomCats(
                        id = cat!!.id,
                        name_cat = cat.name_cat,
                        gender_id = cat.gender_id,
                        age = cat.age,
                        breed_id = cat.breed_id,
                        weight = cat.weight,
                        description_cats = cat.description_cats,
                        image_url = cat.image_url,
                        breed_name = breed?.breed ?: "Неизвестная порода", // Название породы
                        favorite = true
                    )
                }

            }
            catch (e: Exception){
                Log.d("FavoritesVM-GetCats", e.message.toString())
            }
        }
    }

}