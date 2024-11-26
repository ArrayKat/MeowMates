package com.example.meowmates.view.screens.message.ListChats

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.meowmates.model.customModel.CustomCats
import com.example.meowmates.model.database.Chats
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ListChatsViewModel @Inject constructor(): ViewModel() {
    var chatsList = mutableStateOf<List<Chats>>(emptyList())
    fun GetChats(){

    }

}