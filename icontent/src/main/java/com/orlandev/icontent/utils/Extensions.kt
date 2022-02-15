package com.orlandev.icontent.utils

import androidx.annotation.DrawableRes
import com.orlandev.icontent.models.IContentType

fun String.toIContentType(@DrawableRes noImageFound: Int): IContentType {
    return when (this) {
        "text" -> IContentType.Text
        "image" -> IContentType.Image(noImageFound = noImageFound)
        "pano" -> IContentType.Pano
        "video" -> IContentType.Video
        else -> IContentType.Undefined
    }
}