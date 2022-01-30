package com.orlandev.icontent

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.annotation.ExperimentalCoilApi
import com.ondev.imageblurkt_lib.ImageBlur
import com.orlandev.icontent.composables.IText
import com.orlandev.icontent.models.Content
import com.orlandev.icontent.utils.FIELD_IMAGE_BLUR_DELIMITIER


sealed interface ContentType {
    object Text : ContentType
    object Image : ContentType
    object Video : ContentType
    object Pano : ContentType
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun IContent(content: Content, modifier: Modifier = Modifier) {
    when (content.type) {
        is ContentType.Text -> {
            IText(
                text = content.field,
                modifier = modifier
            )
        }
        is ContentType.Image -> {
            val imgRef = content.field.split(FIELD_IMAGE_BLUR_DELIMITIER)
            if (imgRef.size == 2) {
                ImageBlur(
                    modifier = modifier,
                    imageUrl = imgRef[0], //Image URL
                    blurhash = imgRef[1], //Blurhash
                    resources = LocalContext.current.resources,
                    contentDescription = null
                )
            }
        }
        is ContentType.Video -> {
            TODO("NOT IMPLEMENTED YET")
        }
        is ContentType.Pano -> {
            TODO("NOT IMPLEMENTED YET")
        }
    }
}


