package com.orlandev.icontent.models

sealed class ContentUIType {
    //TODO ( CHANGE TEXT TO A CLASS AND ADD TO THIS THE STYLE FOR THE TEXT )

    companion object {
        fun fromString(typeName: String): ContentUIType {
            return when (typeName) {
                "text" -> Text
                "extendText" -> ExtendText
                "image" -> Image
                "video" -> Video
                "pano" -> Pano
                "carousel" -> Carousel
                "beforeAfter" -> BeforeAfter
                else -> Undefined
            }
        }
    }

    object Text : ContentUIType()
    object ExtendText : ContentUIType()
    object Image : ContentUIType()
    object Video : ContentUIType()
    object Pano : ContentUIType()
    object Carousel : ContentUIType()
    object BeforeAfter : ContentUIType()
    object Undefined : ContentUIType()
}