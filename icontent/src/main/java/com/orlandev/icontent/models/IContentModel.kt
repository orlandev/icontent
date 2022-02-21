package com.orlandev.icontent.models

import android.app.Activity
import androidx.annotation.DrawableRes

data class IContentModel(
    val `field`: String,
    val typeI: IContentType,
    @DrawableRes val noImageFound: Int? = null,
    val contextActivity: Activity? = null, //Panoview need the activity context to work
)