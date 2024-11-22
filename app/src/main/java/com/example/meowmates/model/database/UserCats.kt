package com.example.meowmates.model.database

import kotlinx.serialization.Serializable

@Serializable
data class UserCats (
    val id: Int =0,
    val id_user: String="",
    val id_cats: Int=0
)