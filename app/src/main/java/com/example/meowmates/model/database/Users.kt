package com.example.meowmates.model.database

import kotlinx.datetime.DateTimeUnit
import kotlinx.serialization.Serializable

@Serializable
data class Users (
    var id: String = "",
    var surname: String ="",
    var name: String ="",
    var patronymic:String="",
    var birthday:String? = "",
    var image_url: String? ="",
    var telephone: String? =""
)