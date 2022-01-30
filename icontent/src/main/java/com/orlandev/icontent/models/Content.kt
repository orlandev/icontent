package com.orlandev.icontent.models

import com.orlandev.icontent.ContentType

data class Content(
    val `field`: String,
    val type: ContentType,
    val markups: List<Markup> = emptyList()
)