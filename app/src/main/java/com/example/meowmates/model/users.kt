package com.example.meowmates.model

import kotlinx.datetime.DateTimeUnit
import kotlinx.serialization.Serializable

@Serializable
data class users (
    val id: Int =0,
    val surname: String ="",
    val name: String ="",
    val patronymic:String="",
    val birthday:DateTimeUnit.DateBased,
    val image_url: String=""
)