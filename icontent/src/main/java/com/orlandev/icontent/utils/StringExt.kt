package com.orlandev.icontent.utils

import com.orlandev.icontent.components.carousel.CarouselModel
import com.orlandev.icontent.models.ContentModel
import com.orlandev.icontent.models.IContentType

fun String.toIContentType(): IContentType {
    return when (this) {
        "text" -> IContentType.Text
        "htmlText" -> IContentType.HtmlText
        "extendText" -> IContentType.ExtendText
        "extendHtmlText" -> IContentType.ExtendHtmlText
        "image" -> IContentType.Image
        "pano" -> IContentType.Pano
        "video" -> IContentType.Video
        "carousel" -> IContentType.Carousel
        else -> IContentType.Undefined
    }
}

fun String.toCarouselModelList(): List<CarouselModel> {
    val splitData = this.split(FIELD_IMAGE_BLUR_DELIMITIER)
    val listCarouselModel = mutableListOf<CarouselModel>()

    (0..(splitData.size - 2) step 2).forEach { urlIndex ->
        val blurHashIndex = urlIndex + 1
        val imageContent = splitData[urlIndex].generateImageContentField(splitData[blurHashIndex])
        listCarouselModel.add(
            CarouselModel(
                image = imageContent
            )
        )
    }
    return listCarouselModel
}

fun String.generateImageContentField(
    blurHash: String,
    type: IContentType = IContentType.Image
): ContentModel {
    return ContentModel(
        field = "$this$FIELD_IMAGE_BLUR_DELIMITIER$blurHash",
        typeI = type
    )
}

fun String.smartTruncate(max: Int = 120, suffix: String = "..."): String {
    return if (this.length < max) {
        this
    } else {
        "${this.substring(0, this.substring(0, max - suffix.length).lastIndexOf(' '))}${suffix}"
    }
}

