package com.example.meowmates.model.database

import kotlinx.serialization.Serializable

@Serializable
data class chat_members (
    val id: Int =0,
    val chat_id: Int =0,
    val user_id: Int =0
)