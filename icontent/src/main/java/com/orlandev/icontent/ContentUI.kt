package com.orlandev.icontent

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.orlandev.icontent.components.*
import com.orlandev.icontent.components.carousel.CarouselContainer
import com.orlandev.icontent.models.ContentUIModel
import com.orlandev.icontent.models.ContentUIType
import com.orlandev.icontent.utils.toCarouselModelList
import com.orlandev.icontent.utils.toImageBlurHashModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ContentUI(contentUIModel: ContentUIModel, modifier: Modifier) {
    when (contentUIModel.typeI) {
        is ContentUIType.Text -> {
            HtmlText(
                text = contentUIModel.field,
                modifier = modifier,
                style = contentUIModel.textStyle ?: LocalTextStyle.current
            )
        }
        is ContentUIType.ExtendText -> {
            ExtendText(
                text = contentUIModel.field,
                modifier = modifier,
                style = contentUIModel.textStyle ?: LocalTextStyle.current
            )
        }
        is ContentUIType.Image -> {

            ImageBlurHash(
                model = contentUIModel.field.toImageBlurHashModel(),
                modifier = modifier,
                notImageFound = R.drawable.no_image
            )
        }
        is ContentUIType.Video -> {
            //TODO(" ADD VIDEO PROPERTIES")
            VideoPlayer(modifier = modifier, url = contentUIModel.field)
        }
        is ContentUIType.Pano -> {
            PanoView(
                model = contentUIModel.field.toImageBlurHashModel(),
                modifier = modifier.fillMaxWidth()
            )
        }

        is ContentUIType.Carousel -> {
            CarouselContainer(modifier = modifier,
                carouselDataList = contentUIModel.field.toCarouselModelList(),
                onCarouselItemClick = {})
        }
        is ContentUIType.BeforeAfter -> {

            ImageBeforeAfter(modifier = modifier, contentUIModel = contentUIModel)

        }

        else -> {
            TODO("CONTENT TYPE IS UNDEFINED - TYPE: ${contentUIModel.typeI}")
        }
    }

}
