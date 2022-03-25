package com.orlandev.icontent.models

sealed class IContentType(typeName: String) {
    //TODO ( CHANGE TEXT TO A CLASS AND ADD TO THIS THE STYLE FOR THE TEXT )
    object Text : IContentType("text")
    object HtmlText : IContentType("htmlText")
    object ExtendHtmlText : IContentType("extendText")
    object ExtendText : IContentType("extendHtmlText")
    object Image : IContentType("image")
    object Video : IContentType("pano")
    object Pano : IContentType("video")
    object Undefined : IContentType("Undefined")
}