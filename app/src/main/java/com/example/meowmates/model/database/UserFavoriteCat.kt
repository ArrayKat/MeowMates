package com.example.meowmates.model.database

import kotlinx.serialization.Serializable

@Serializable
data class UserFavoriteCat (
    val id: Int=0,
    val id_user: String="",
    val id_cat: Int =0
)