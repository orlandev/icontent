package com.orlandev.icontent.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.annotation.ExperimentalCoilApi
import com.ondev.imageblurkt_lib.AsyncBlurImage
import com.ondev.imageblurkt_lib.IBlurModel
import com.orlandev.icontent.R
import com.orlandev.icontent.models.ContentModel
import com.orlandev.icontent.models.IContentType
import com.orlandev.icontent.utils.FIELD_IMAGE_BLUR_DELIMITIER

@OptIn(ExperimentalCoilApi::class)
@Composable
fun IImageBlur(
    modifier: Modifier,
    contentModel: ContentModel,
    contentType: IContentType,
    contentScale: ContentScale = ContentScale.Crop,
    addGradient: Boolean = false,
    gradientColor: Color = MaterialTheme.colorScheme.background,
    orientation: GradientEffectOrientation = GradientEffectOrientation.Vertically,
    align: GradientAlignment = GradientAlignment.End,
    alphaValue: Float = 0.9f
) {
    val data = remember {
        derivedStateOf {
            val imgRef = contentModel.field.split(FIELD_IMAGE_BLUR_DELIMITIER)
            if (imgRef.size == 2 && contentType is IContentType.Image) {
                IBlurModel(imageUrl = imgRef[0], blurHash = imgRef[1])
            } else {
                null
            }
        }
    }

    data.value?.let { currentData ->
        Box(modifier = Modifier.wrapContentSize()) {
            AsyncBlurImage(
                modifier = modifier,
                data = currentData,
                notImageFoundRes = contentModel.noImageFound ?: R.drawable.no_image,
                contentDescription = null,
                contentScale = contentScale
            )
            if (addGradient) {
                IGradientEffect(
                    backgroundColor = gradientColor,
                    orientation = orientation,
                    align = align,
                    alphaValue = alphaValue
                )
            }
        }
    }
}