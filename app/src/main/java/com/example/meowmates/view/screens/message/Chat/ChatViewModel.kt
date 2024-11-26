package com.example.meowmates.view.screens.message.Chat

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.meowmates.domain.messages.MessagesRepository
import com.example.meowmates.domain.utils.Constants
import com.example.meowmates.model.database.Chats
import com.example.meowmates.model.database.Messages
import com.example.meowmates.model.database.Users
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.filter.FilterOperation
import io.github.jan.supabase.postgrest.query.filter.FilterOperator
import io.github.jan.supabase.realtime.PostgresAction
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresChangeFlow
import io.github.jan.supabase.realtime.realtime
import io.github.jan.supabase.realtime.selectAsFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messagesRepository: MessagesRepository
): ViewModel() {

    private var _isError = MutableStateFlow<Boolean>(false)
    var isError = _isError.asStateFlow()

    private var _messageList : MutableStateFlow<MutableList<Messages>> = MutableStateFlow(mutableListOf())
    val messageList: StateFlow<MutableList<Messages>> = _messageList.asStateFlow()
    private var subscription: Any? = null // храним подписку для отключения
    private var _chatId = 0

    var RecipientFIO:String = "ФИО"
    var textInput = mutableStateOf("")

    fun GetMessage(sender:Users, recipient: Users){
        viewModelScope.launch {
            _chatId = messagesRepository.getChatIdToUsers(sender,recipient)
            try {
                withContext(Dispatchers.IO) {
                    @OptIn(SupabaseExperimental::class)
                    val messageFlow: Flow<List<Messages>> =
                        Constants.provideSupabaseClient().postgrest.from("messages")
                            .selectAsFlow(
                                Messages::id,
                                filter = FilterOperation(
                                    "chat_id",
                                    FilterOperator.EQ,
                                    _chatId
                                )
                            )
                    messageFlow.collect {
                        _messageList.value = it.toMutableList()
                        Log.d("ChatVM", "Успешно получено сообщение GetMessage: ${it.toString()}")
                        Log.d("ChatVM", "Успешно получено сообщение GetMessage: ${it.toList()}")
                        Log.d("ChatVM", "Успешно получено сообщение GetMessage: ${it.toMutableList()}")
                    }

                }
            } catch (e: Exception) {
                Log.d("ChatVM", "Произошла ошибк в методе GetMessage: ${e.message}")
            }
        }
    }

//    fun subscribeToMessages(scope: CoroutineScope) {
//        viewModelScope.launch {
//            var messageFlow: Flow<List<Messages>>
//            val channel  = Constants.supabase.channel("messages")
//            val changeFlow = channel.postgresChangeFlow<PostgresAction.Insert>(schema = "public"){
//                table = "messages"
//            }
//            changeFlow.onEach {
//                when(it){
//                    is PostgresAction.Insert -> {
//                        val stringfieldData = it.record.toString()
//                        val data = Json.decodeFromString<Messages>(stringfieldData)
//                        _messageList.value.add(data)
//                    }
//
//                }
//            }.launchIn(scope)
//            Log.d("ChatVM", "Успешно подписаны (subscribeToMessages) ")
//            channel.subscribe()
//
//        }
//
//
//    }
//    private fun GetChatId(sender:String, recipient: String){
//        viewModelScope.launch {
//            try {
//                var chatId = Constants.supabase.from("chats").select {
//                    filter {
//                        or {
//                            and {
//                                eq("first_user", sender)
//                                eq("second_user", recipient)
//                            }
//                            and {
//                                eq("first_user", recipient)
//                                eq("second_user", sender)
//                            }
//                        }
//                    }
//                }.decodeSingle<Chats>().id
//                Log.d("ChatVM", "Успешно получен чат в методе GetChats: ${chatId}")
//            }
//            catch (e:Exception){
//                chatId = -1
//                Log.d("ChatVM", "Возникла ошибка в методе GetChats: ${e.message}")
//            }
//        }
//    }

    fun SendMessage(sender: Users){
        val newMessage = Messages(
            text = textInput.value,
            sender_id = sender.id,
            chat_id = _chatId
        )
        viewModelScope.launch {
            try{
                _isError.value = messagesRepository.insertNewMessages(newMessage)
            }
            catch (e:Exception){
                Log.d("ChatViewModel", "Ошибка в методе SendMessage: ${e.message}")
            }
        }
    }
    fun GetUser(idUser: String): Users {
        var user = Users()
        viewModelScope.launch {
            user = Constants.supabase.from("users").select { filter { eq("id",idUser) } }.decodeSingle<Users>()
        }
        return user
    }
}