package com.example.meowmates.model.database

import kotlinx.serialization.Serializable

@Serializable
data class Cats (
    var id: Int =0,
    var name_cat: String = "",
    var gender_id: Int =0,
    var age: Int=0,
    var breed_id: Int=0,
    var weight: Int = 0,
    var description_cats: String ="",
    var image_url: String =""
)