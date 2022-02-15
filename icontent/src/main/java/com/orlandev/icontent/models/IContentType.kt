package com.orlandev.icontent.models

import androidx.annotation.DrawableRes

sealed interface IContentType {
    //TODO ( CHANGE TEXT TO A CLASS AND ADD TO THIS THE STYLE FOR THE TEXT )
    object Text : IContentType
    data class Image(@DrawableRes val noImageFound: Int) : IContentType
    object Video : IContentType
    object Pano : IContentType
    object Undefined : IContentType
}