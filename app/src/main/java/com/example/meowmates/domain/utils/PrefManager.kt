package com.example.meowmates.domain.utils

import android.content.Context
import android.content.SharedPreferences

//объект для того, что бы сохранять локального пользователя, которого мы авторизировали в системе
object PrefManager {
    //переменная объекта для доступа к значениям:
    private lateinit var statusSystem: SharedPreferences
    //функция, которая получает доступ к хранилищу приложения:
    fun init(context: Context){
        statusSystem = context.getSharedPreferences("statusSystem", Context.MODE_PRIVATE)
    }
    //свойство, для получения, установки значения локального юзера, который зарегестипровался и авторизовался с этого телефона:
    var currentUser: String?
        get() = statusSystem.getString("currentUser", null)
        set(value) = statusSystem.edit().putString("currentUser", value).apply()
    }