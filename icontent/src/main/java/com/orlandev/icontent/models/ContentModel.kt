package com.orlandev.icontent.models

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class ContentModel(
    val field: String,
    val typeI: IContentType,
    val textStyle: androidx.compose.ui.text.TextStyle? = null,
    val textColor: Color? = null,
    @DrawableRes val noImageFound: Int? = null,
    val contextActivity: Activity? = null, //Panoview need the activity context to work
)