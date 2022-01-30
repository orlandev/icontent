package com.orlandev.icontent.models

data class IContentModel(
    val `field`: String,
    val typeI: IContentType,
    val markups: List<Markup> = emptyList()
)