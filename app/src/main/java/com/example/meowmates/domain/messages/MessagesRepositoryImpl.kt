package com.example.meowmates.domain.messages

import com.example.meowmates.model.database.Chats
import com.example.meowmates.model.database.Messages
import com.example.meowmates.model.database.Users
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest
) :MessagesRepository {
    override suspend fun getChatIdToUsers(sender: Users, recipient: Users): Int {
        return try {
            withContext(Dispatchers.IO) {
                val chat = postgrest.from("chats").select {
                    filter {
                        or {
                            and {
                                eq("first_user", sender.id)
                                eq("second_user", recipient.id)
                            }
                            and {
                                eq("second_user", sender.id)
                                eq("first_user", recipient.id)
                            }
                        }
                    }
                }.decodeSingle<Chats>()
                chat.id
            }
        }catch (e:Exception){
            -1
        }
    }

    override suspend fun insertNewMessages(newMessage: Messages): Boolean {
        return try{
            withContext(Dispatchers.IO){
                postgrest.from("messages").insert(newMessage)
                false
            }
        }
        catch (e:Exception){
            true
        }
    }
}