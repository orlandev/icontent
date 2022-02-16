package com.orlandev.icontent.utils

import com.orlandev.icontent.models.IContentType

fun String.toIContentType(): IContentType {
    return when (this) {
        "text" -> IContentType.Text
        "image" -> IContentType.Image
        "pano" -> IContentType.Pano
        "video" -> IContentType.Video
        else -> IContentType.Undefined
    }
}