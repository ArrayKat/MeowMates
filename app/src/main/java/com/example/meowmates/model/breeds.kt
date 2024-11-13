package com.example.meowmates.model

import kotlinx.serialization.Serializable

@Serializable
data class breed (
    val id: Int =0,
    val breed: String = ""
)