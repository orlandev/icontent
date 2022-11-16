package com.orlandev.icontent.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


suspend fun getBitmapFromURL(strURL: String?): Bitmap? {
    try {
        val bitmap = withContext(Dispatchers.IO) {

            val url = URL(strURL)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        }
        return bitmap

    } catch (e: IOException) {
        e.printStackTrace()
        return null
    }
}