package com.orlandev.icontent.utils

import com.orlandev.icontent.models.IContentType

fun String.toIContentType(): IContentType {
    return when (this) {
        "text" -> IContentType.Text
        "htmlText" -> IContentType.HtmlText
        "expandText" -> IContentType.ExtendText
        "expandHtmlText" -> IContentType.ExpandableHtmlText
        "image" -> IContentType.Image
        "pano" -> IContentType.Pano
        "video" -> IContentType.Video
        else -> IContentType.Undefined
    }
}

fun String.smartTruncate(max: Int = 120, suffix: String = "..."): String {
    return if (this.length < max) {
        this
    } else {
        "${this.substring(0, this.substring(0, max - suffix.length).lastIndexOf(' '))}${suffix}"
    }
}

