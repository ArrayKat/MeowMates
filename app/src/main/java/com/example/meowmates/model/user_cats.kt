package com.example.meowmates.model

import kotlinx.serialization.Serializable

@Serializable
data class user_cats (
    val id: Int =0,
    val id_user: Int =0,
    val id_cats: Int=0
)