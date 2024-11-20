package com.example.meowmates.view.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meowmates.domain.utils.Constants
import com.example.meowmates.model.Cats
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor():ViewModel() {
    var catsList = mutableStateOf<List<Cats>>(emptyList())
    init{
        GetCats()
    }
    private fun GetCats(){
        viewModelScope.launch {
            try{
                val response = Constants.supabase.from("cats").select().decodeList<Cats>()
                if(response.isEmpty()){
                    Log.d("HomeVM", "У вас проблемы, котики пустые")
                }

                catsList.value = response

            }
            catch (e: Exception){
                Log.d("HomeView", e.message.toString())
            }
        }
    }
}