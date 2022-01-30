package com.orlandev.icontent.models

import androidx.annotation.DrawableRes

sealed interface IContentType {
    object Text : IContentType
    class Image(@DrawableRes val noImageFound: Int) : IContentType
    object Video : IContentType
    object Pano : IContentType
}