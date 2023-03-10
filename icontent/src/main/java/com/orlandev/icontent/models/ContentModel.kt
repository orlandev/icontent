package com.orlandev.icontent.models

import androidx.compose.ui.text.TextStyle

data class ContentModel(
    val field: String,
    val typeI: IContentType,
    val textStyle: TextStyle? = null,
    val noImageFound: Any? = null,
)