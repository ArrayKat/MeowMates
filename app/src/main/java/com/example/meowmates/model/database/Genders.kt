package com.example.meowmates.model.database

import kotlinx.serialization.Serializable

@Serializable
data class genders (
    val id: Int =0,
    val gender: String =""
)