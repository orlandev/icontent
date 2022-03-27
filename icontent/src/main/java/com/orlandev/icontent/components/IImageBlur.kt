package com.orlandev.icontent.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.annotation.ExperimentalCoilApi
import com.ondev.imageblurkt_lib.ImageBlur
import com.orlandev.icontent.R
import com.orlandev.icontent.models.ContentModel
import com.orlandev.icontent.models.IContentType
import com.orlandev.icontent.utils.FIELD_IMAGE_BLUR_DELIMITIER

@OptIn(ExperimentalCoilApi::class)
@Composable
fun IImageBlur(modifier: Modifier, contentModel: ContentModel, contentType: IContentType) {
    val currentResources = LocalContext.current.resources
    val imgRef = contentModel.field.split(FIELD_IMAGE_BLUR_DELIMITIER)
    if (imgRef.size == 2 && contentType is IContentType.Image) {
        ImageBlur(
            modifier = modifier,
            imageUrl = imgRef[0], //Image URL
            blurhash = imgRef[1], //Blurhash
            resources = currentResources,
            notImageFoundRes = contentModel.noImageFound ?: R.drawable.no_image,
            contentDescription = null
        )
    } else {
        throw Exception("Invalid content type")
    }
}