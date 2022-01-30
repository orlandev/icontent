package com.orlandev.icontent.models

import android.content.Context
import androidx.annotation.DrawableRes

sealed interface IContentType {
    //TODO ( CHANGE TEXT TO A CLASS AND ADD TO THIS THE STYLE FOR THE TEXT )
    object Text : IContentType
    class Image(@DrawableRes val noImageFound: Int, val context: Context) : IContentType
    object Video : IContentType
    object Pano : IContentType
}