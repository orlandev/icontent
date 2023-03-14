package com.orlandev.icontent.utils

import com.ondev.imageblurkt_lib.ImageBlurHashModel
import com.orlandev.icontent.components.carousel.CarouselModel
import com.orlandev.icontent.models.ContentUIModel
import com.orlandev.icontent.models.ContentUIType



fun String.toImageBlurHashModel(): ImageBlurHashModel {
    val imgRef = this.split(FIELD_IMAGE_BLUR_DELIMITIER)
    val imgData = if (imgRef.size == 2) {
        ImageBlurHashModel(data = imgRef.first(), blurHash = imgRef[1])
    } else {
        ImageBlurHashModel(data = "", blurHash = "")
    }

    return imgData

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
    blurHash: String, type: ContentUIType = ContentUIType.fromString("image")
): ContentUIModel {
    return ContentUIModel(
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

