package com.orlandev.icontent.composables

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.annotation.ExperimentalCoilApi
import com.ondev.imageblurkt_lib.ImageBlur
import com.orlandev.icontent.models.IContentType
import com.orlandev.icontent.utils.FIELD_IMAGE_BLUR_DELIMITIER

@OptIn(ExperimentalCoilApi::class)
@Composable
  fun IImageBlur(field: String, modifier: Modifier, contentType: IContentType) {
    val imgRef = field.split(FIELD_IMAGE_BLUR_DELIMITIER)
    if (imgRef.size == 2 && contentType is IContentType.Image) {
        ImageBlur(
            modifier = modifier,
            imageUrl = imgRef[0], //Image URL
            blurhash = imgRef[1], //Blurhash
            resources = contentType.context.resources,
            notImageFoundRes = contentType.noImageFound,
            contentDescription = null
        )
    }
}