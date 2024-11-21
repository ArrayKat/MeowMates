package com.example.meowmates.domain.utils

import android.content.Context
import android.content.SharedPreferences

object PrefManager {
    private lateinit var statusSystem: SharedPreferences
    fun init(context: Context){
        statusSystem = context.getSharedPreferences("statusSystem", Context.MODE_PRIVATE)
    }
    var currentUser: String?
        get() = statusSystem.getString("currentUser", null)
        set(value) = statusSystem.edit().putString("currentUser", value).apply()
    }