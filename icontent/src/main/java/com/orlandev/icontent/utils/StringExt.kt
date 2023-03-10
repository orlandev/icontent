package com.orlandev.icontent.utils

import com.orlandev.icontent.components.carousel.CarouselModel
import com.orlandev.icontent.models.ContentModel
import com.orlandev.icontent.models.IContentType

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
    blurHash: String, type: IContentType = IContentType.fromString("image")
): ContentModel {
    return ContentModel(
        field = "$this$FIELD_IMAGE_BLUR_DELIMITIER$blurHash", typeI = type
    )
}

fun String.smartTruncate(max: Int = 120, suffix: String = "..."): String {
    return if (this.length < max) {
        this
    } else {
        "${this.substring(0, this.substring(0, max - suffix.length).lastIndexOf(' '))}${suffix}"
    }
}

/**
 *  Use this function to add height and width to the
 *  ImageKit CDN url request
 *
 *  tr - is Transformation
 *  h- is the height
 *  w- is the weidhr
 *  fo-auto - is SmartCrop
 *
 *  Read the ImageKitIO Documentation about this
 *  https://imagekit.io/blog/smart-crop-intelligent-image-cropping-imagekit/
 *
 */
fun String.imageResizeTo(h: Int = 120, w: Int = 120, addFo: Boolean = false): String {
    return "$this?tr=h-$h,w-$w,${if (addFo) "fo-auto" else ""}"
}

