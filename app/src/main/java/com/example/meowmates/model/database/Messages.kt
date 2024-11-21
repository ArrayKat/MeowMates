package com.example.meowmates.model.database

import kotlinx.datetime.DateTimeUnit
import kotlinx.serialization.Serializable

@Serializable
data class messages (
    val id: Int = 0,
    val text: String ="",
    val send_date: DateTimeUnit.DateBased,
    val sender_id: Int =0,
    val chat_id: Int =0
)