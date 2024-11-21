package com.example.meowmates.model.customModel

data class CustomCats (
    val id: Int =0,
    val name_cat: String = "",
    val gender_id: Int =0,
    val age: Int=0,
    val breed_id: Int=0,
    val weight: Int = 0,
    val description_cats: String ="",
    val image_url: String="",
    val breed_name:String = "",
    var favorite: Boolean = false
)