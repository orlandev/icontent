package com.orlandev.icontent.models

import androidx.compose.ui.text.TextStyle

data class ContentUIModel(
    val field: String,
    val typeI: ContentUIType,
    val textStyle: TextStyle? = null,
    val noImageFound: Any? = null,
)