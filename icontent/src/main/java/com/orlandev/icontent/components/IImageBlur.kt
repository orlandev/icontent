package com.orlandev.icontent.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.annotation.ExperimentalCoilApi
import com.ondev.imageblurkt_lib.AsyncImageBlurHash
import com.ondev.imageblurkt_lib.ImageBlurHashModel
import com.orlandev.icontent.models.ContentUIModel
import com.orlandev.icontent.models.ContentUIType
import com.orlandev.icontent.utils.FIELD_IMAGE_BLUR_DELIMITIER

@Deprecated(
    message = "This component will be deleted in teh future."
)
@OptIn(ExperimentalCoilApi::class)
@Composable
fun IImageBlur(
    modifier: Modifier,
    contentUIModel: ContentUIModel,
    contentType: ContentUIType,
    contentScale: ContentScale = ContentScale.Crop,
    addGradient: Boolean = false,
    gradientColor: Color = MaterialTheme.colorScheme.background,
    orientation: GradientEffectOrientation = GradientEffectOrientation.Vertically,
    align: GradientAlignment = GradientAlignment.End,
    alphaValue: Float = 0.9f
) {
    val data = remember {
        derivedStateOf {
            val imgRef = contentUIModel.field.split(FIELD_IMAGE_BLUR_DELIMITIER)
            if (imgRef.size == 2 && contentType is ContentUIType.Image) {
                ImageBlurHashModel(data = imgRef[0], blurHash = imgRef[1])
            } else {
                null
            }
        }
    }

    data.value?.let { currentData ->
        Box(modifier = Modifier.wrapContentSize()) {
            AsyncImageBlurHash(
                modifier = modifier,
                model = currentData,
                notImageFoundRes = contentUIModel.noImageFound,
                contentDescription = null,
                contentScale = contentScale
            )
            if (addGradient) {
                GradientEffect(
                    backgroundColor = gradientColor,
                    orientation = orientation,
                    align = align,
                    alphaValue = alphaValue
                )
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageBlurHash(
    modifier: Modifier,
    model: ImageBlurHashModel,
    notImageFound: Any,
    contentScale: ContentScale = ContentScale.Crop,
    addGradient: Boolean = false,
    gradientColor: Color = MaterialTheme.colorScheme.background,
    orientation: GradientEffectOrientation = GradientEffectOrientation.Vertically,
    align: GradientAlignment = GradientAlignment.End,
    alphaValue: Float = 0.9f
) {
    Box(modifier = modifier) {
        AsyncImageBlurHash(
            modifier = Modifier.fillMaxSize(),
            model = model,
            notImageFoundRes = notImageFound,
            contentDescription = null,
            contentScale = contentScale
        )
        if (addGradient) {
            GradientEffect(
                backgroundColor = gradientColor,
                orientation = orientation,
                align = align,
                alphaValue = alphaValue
            )
        }
    }
}