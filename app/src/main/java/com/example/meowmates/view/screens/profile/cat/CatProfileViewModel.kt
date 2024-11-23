package com.example.meowmates.view.screens.profile.cat

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.meowmates.domain.utils.Constants
import com.example.meowmates.domain.utils.PrefManager.currentUser
import com.example.meowmates.model.database.Cats
import com.example.meowmates.model.database.UserCats
import com.example.meowmates.view.navigation.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import javax.inject.Inject


@HiltViewModel
class CatProfileViewModel @Inject constructor(): ViewModel() {
    var currentIdCat = mutableStateOf(0)
    val name = mutableStateOf("")
    val gender = mutableStateOf("")
    val age = mutableStateOf("")
    val breed = mutableStateOf("")
    val weight = mutableStateOf("")
    val descriptor = mutableStateOf("")
    val image_url = mutableStateOf("")


    fun GetCat() {
        viewModelScope.launch {

            if (currentIdCat.value != 0) {
                var cat = Constants.supabase.from("cats")
                    .select { filter { eq("id", currentIdCat.value) } }.decodeSingle<Cats>()
                Log.d("CatProfileVM", "Получили кота, юхуууу")
                name.value = cat.name_cat
                gender.value = cat.gender_id.toString()
                age.value = cat.age.toString()
                breed.value = cat.breed_id.toString()
                weight.value = cat.weight.toString()
                descriptor.value = cat.description_cats
                image_url.value = cat.image_url
            } else {
                Log.d("CatProfileVM", "Начинаем создавать кота")
            }
        }
    }
    fun SaveChangeCat(controller: NavHostController) {
        viewModelScope.launch {
            if (currentIdCat.value!= 0) { //изменяем кота
                GetCat()
                try {
                    //изменение кота:
                    val updateCat = Cats(
                        name_cat = name.value,
                        gender_id = gender.value.toInt(),
                        age = age.value.toInt(),
                        breed_id = breed.value.toInt(),
                        weight = weight.value.toInt(),
                        description_cats = descriptor.value,
                        image_url = image_url.value
                    )
                    val responce = Constants.supabase.from("cats")
                        .update(updateCat) { filter { eq("id", currentIdCat.value) } }
                    Log.d("CatProfileVM", "Изменяем кота: ${name.value}")
                    Log.d("CatProfileVM", "ID кота: ${currentIdCat.value}")
                } catch (e: Exception) {
                    Log.d(
                        "CatProfileVM",
                        "Не удалось сохранить изменения: ${e.message.toString()}"
                    )
                }

            } else { //добавляем
                try {
                    val cat = Cats(
                        name_cat = name.value,
                        gender_id = gender.value.toInt(),
                        age = age.value.toInt(),
                        breed_id = breed.value.toInt(),
                        weight = weight.value.toInt(),
                        description_cats = descriptor.value,
                        image_url = ""
                    )
                    //добавляем и получаем объект добавленного кота
                    val catDb = Constants.supabase.from("cats").insert(cat) { select() }
                        .decodeSingle<Cats>()

                    Log.d("CatProfileVM", "Добавляем кота: ${catDb.id}. ${catDb.name_cat}")
                    //создаем объект связи нового кота и пользователя
                    val user_cat = UserCats(
                        id_user = currentUser.toString(),
                        id_cats = catDb.id
                    )
                    //добавляем этот объект
                    val ansv = Constants.supabase.from("user_cats").insert(user_cat)
                } catch (e: Exception) {
                    Log.d(
                        "CatProfileVM",
                        "Не удалось добавить изменения: ${e.message.toString()}"
                    )
                }

            }
            controller.navigate(NavigationRoutes.MAINPROFILE) {
                popUpTo(NavigationRoutes.CATPROFILE) {
                    inclusive = true
                }
            }
        }
    }
}