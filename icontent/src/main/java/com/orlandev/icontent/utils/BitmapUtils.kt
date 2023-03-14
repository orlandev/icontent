package com.orlandev.icontent.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.transform.CircleCropTransformation


suspend fun fetchImageAsBitmap(
    ctx: Context, data: Any, cropCircular: Boolean = false
): Bitmap? {
    return try {
        val loading = ImageLoader(ctx)
        val request = if (cropCircular) ImageRequest.Builder(ctx).allowConversionToBitmap(true)
            .allowHardware(false).data(data).transformations(CircleCropTransformation()).build()
        else ImageRequest.Builder(ctx).allowConversionToBitmap(true).allowHardware(false).data(data)
            .build()

        val result = (loading.execute(request) as SuccessResult).drawable
        (result as BitmapDrawable).bitmap
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}