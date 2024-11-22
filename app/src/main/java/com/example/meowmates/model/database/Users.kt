package com.example.meowmates.model.database

import kotlinx.datetime.DateTimeUnit
import kotlinx.serialization.Serializable

@Serializable
data class Users (
    val id: String = "",
    val surname: String ="",
    val name: String ="",
    val patronymic:String="",
    val birthday:String? = "",
    val image_url: String? ="",
    val telephone: String? =""
)