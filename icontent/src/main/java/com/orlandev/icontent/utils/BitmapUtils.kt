package com.orlandev.icontent.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


suspend fun getCoilBitmap(ctx: Context, url: String): Bitmap {
    val loading = ImageLoader(ctx)
    val request = ImageRequest.Builder(ctx)
        .allowConversionToBitmap(true)
        .allowHardware(false)
        .data(url)
        .build()
    val result = (loading.execute(request) as SuccessResult).drawable
    return (result as BitmapDrawable).bitmap
}