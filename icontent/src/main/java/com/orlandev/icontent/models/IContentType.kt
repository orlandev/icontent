package com.orlandev.icontent.models

sealed class IContentType(typeName: String) {
    //TODO ( CHANGE TEXT TO A CLASS AND ADD TO THIS THE STYLE FOR THE TEXT )

    companion object {
        fun fromString(typeName: String): IContentType {
            return when (typeName) {
                "text" -> Text(typeName)
                "htmlText" -> HtmlText(typeName)
                "image" -> Image(typeName)
                "video" -> Video(typeName)
                "extendText" -> ExtendText(typeName)
                "extendHtmlText" -> ExtendHtmlText(typeName)
                "pano" -> Pano(typeName)
                "carousel" -> Carousel(typeName)
                else -> Undefined(typeName)
            }
        }
    }

    class Text(typeName: String) : IContentType("text")
    class HtmlText(typeName: String) : IContentType("htmlText")
    class ExtendHtmlText(typeName: String) : IContentType("extendText")
    class ExtendText(typeName: String) : IContentType("extendHtmlText")
    class Image(typeName: String) : IContentType("image")
    class Video(typeName: String) : IContentType("video")
    class Pano(typeName: String) : IContentType("pano")
    class Carousel(typeName: String) : IContentType("carousel")
    class Undefined(typeName: String) : IContentType("Undefined")
}