package com.example.meowmates.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.meowmates.domain.utils.Constants
import com.example.meowmates.domain.utils.PrefManager.currentUser
import com.example.meowmates.model.database.Cats
import com.example.meowmates.model.database.UserFavoriteCat
import com.example.meowmates.model.database.Users
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    fun LikeCat(idCat:Int, isLikeCat: Boolean){
        Log.d("HomeVM - LikeCat" ,"вы перешли на LikeCat!")
        viewModelScope.launch {
            try{
                val favoriteCat = Constants.supabase.from("user_favorite_cats").select().decodeList<Cats>()
                if(!isLikeCat){ //если мы лайкнули кота
                    val newString = UserFavoriteCat(
                        id_cat = idCat,
                        id_user = currentUser!!
                    )
                    Constants.supabase.from("user_favorite_cats").insert(newString)
                    Log.d("HomeVM - LikeCat" ,"Add like cat: "+ idCat+ " user: "+ currentUser!!.toString())
                }
                else{
                    val oldString = Constants.supabase.from("user_favorite_cats").delete{
                        filter {
                            eq("id_cat", idCat)
                            eq("id_user", currentUser!!)
                        }
                    }
                    Log.d("HomeVM - LikeCat" ,"Delete like cat: "+ idCat+ " user: "+ currentUser!!.toString())
                }
            }
            catch (e:Exception){
                Log.d("HomeVM-LikeCat","Что то пошло не так:" + e.message.toString())
            }
        }
    }


}