package com.orlandev.icontent.utils

import android.content.Context
import androidx.annotation.DrawableRes
import com.orlandev.icontent.models.IContentType

fun String.toIContentType(ctx: Context, @DrawableRes noImageFound: Int): IContentType {
    return when (this) {
        "text" -> IContentType.Text
        "image" -> {
            IContentType.Image(context = ctx, noImageFound = noImageFound)
        }
        "pano" -> IContentType.Pano
        "video" -> IContentType.Video
        else -> {
            IContentType.Undefined
        }
    }
}