package com.orlandev.icontent.models

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.material3.LocalTextStyle
import androidx.compose.ui.text.font.FontStyle
import java.time.format.TextStyle

data class ContentModel(
    val field: String,
    val typeI: IContentType,
    val textStyle: androidx.compose.ui.text.TextStyle? = null,
    @DrawableRes val noImageFound: Int? = null,
    val contextActivity: Activity? = null, //Panoview need the activity context to work
)