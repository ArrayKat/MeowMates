package com.example.meowmates.model.database

import kotlinx.serialization.Serializable

@Serializable
data class Breeds (
    val id: Int =0,
    val breed: String = ""
)