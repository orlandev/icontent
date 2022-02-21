package com.orlandev.icontent.utils

import com.orlandev.icontent.models.IContentType

fun String.toIContentType(): IContentType {
    return when (this) {
        "text" -> IContentType.Text
        "htmlText" -> IContentType.HtmlText
        "extendtext" -> IContentType.ExtendText
        "image" -> IContentType.Image
        "pano" -> IContentType.Pano
        "video" -> IContentType.Video
        else -> IContentType.Undefined
    }
}