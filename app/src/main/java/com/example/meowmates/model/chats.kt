package com.example.meowmates.model

import kotlinx.datetime.DateTimeUnit
import kotlinx.serialization.Serializable
import java.sql.Date

@Serializable
data class chats (
    val id: Int =0,
    val creation_date: DateTimeUnit.DateBased
)