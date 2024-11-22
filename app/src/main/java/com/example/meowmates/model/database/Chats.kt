package com.example.meowmates.model.database

import kotlinx.datetime.DateTimeUnit
import kotlinx.serialization.Serializable

@Serializable
data class Chats (
    val id: Int =0,
    val creation_date: DateTimeUnit.DateBased
)