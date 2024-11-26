package com.example.meowmates.model.database

import kotlinx.datetime.DateTimeUnit
import kotlinx.serialization.Serializable

@Serializable
data class Chats (
    val id: Int =0,
    val first_user:String,
    val second_user:String
)