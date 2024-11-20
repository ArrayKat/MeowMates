package com.example.meowmates.model

import kotlinx.datetime.DateTimeUnit
import kotlinx.serialization.Serializable
import java.sql.Date

@Serializable
data class messages (
    val id: Int = 0,
    val text: String ="",
    val send_date: DateTimeUnit.DateBased,
    val sender_id: Int =0,
    val chat_id: Int =0
)