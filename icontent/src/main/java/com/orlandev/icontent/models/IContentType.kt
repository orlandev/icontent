package com.orlandev.icontent.models

sealed interface IContentType {
    //TODO ( CHANGE TEXT TO A CLASS AND ADD TO THIS THE STYLE FOR THE TEXT )
    object Text : IContentType
    object Image : IContentType
    object Video : IContentType
    object Pano : IContentType
    object Undefined : IContentType
}