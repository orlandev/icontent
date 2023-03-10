package com.orlandev.icontent.models

sealed class IContentType {
    //TODO ( CHANGE TEXT TO A CLASS AND ADD TO THIS THE STYLE FOR THE TEXT )

    companion object {
        fun fromString(typeName: String): IContentType {
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

    object Text : IContentType()
    object ExtendText : IContentType()
    object Image : IContentType()
    object Video : IContentType()
    object Pano : IContentType()
    object Carousel : IContentType()
    object BeforeAfter : IContentType()
    object Undefined : IContentType()
}