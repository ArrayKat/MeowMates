package com.example.meowmates.domain.messages

import com.example.meowmates.model.database.Messages
import com.example.meowmates.model.database.Users


interface MessagesRepository {
    suspend fun getChatIdToUsers(sender: Users, recipient: Users): Int
    suspend fun insertNewMessages(newMessage: Messages): Boolean
}