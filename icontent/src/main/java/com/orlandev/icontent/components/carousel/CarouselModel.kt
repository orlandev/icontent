package com.orlandev.icontent.components.carousel

import com.orlandev.icontent.models.ContentModel

data class CarouselModel(
    val image: ContentModel,
    val title: String = "",
    val subtitle: String = "",
    val id: Int = -1,
)