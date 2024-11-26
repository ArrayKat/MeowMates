package com.example.meowmates.model.database

import kotlinx.datetime.DateTimeUnit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Messages (
    val id: Int = 0,
    val text: String ="",
    val sender_id: String ="",
    val chat_id: Int =0
)