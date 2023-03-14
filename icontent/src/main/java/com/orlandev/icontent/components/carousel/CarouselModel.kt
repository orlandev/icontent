package com.orlandev.icontent.components.carousel

import com.orlandev.icontent.models.ContentUIModel
@Deprecated(message = "This will be deleted in the future versions")
data class CarouselModel(
    val image: ContentUIModel,
    val title: String = "",
    val subtitle: String = "",
    val id: Int = -1,
)