package com.example.meowmates.view.converter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import com.example.meowmates.R
import java.io.ByteArrayOutputStream


fun  bitmapToByteArray (context: Context, uri:Uri):ByteArray{
    val inputStream = context.contentResolver.openInputStream(uri)
    val bitmap = BitmapFactory.decodeStream(inputStream)
    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    Log.d("MainProfile", "Картинка преобразована в массив байтов")
    return baos.toByteArray()
}