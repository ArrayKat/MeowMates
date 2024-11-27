package com.example.meowmates.view.converterBitmap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.meowmates.R
import java.io.ByteArrayOutputStream


fun  bitmapToByteArray (context: Context):ByteArray{
    val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.icon_user_default)
    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    return baos.toByteArray()
}