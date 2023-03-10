package com.orlandev.icontent

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.orlandev.icontent.components.*
import com.orlandev.icontent.components.carousel.CarouselContainer
import com.orlandev.icontent.models.ContentModel
import com.orlandev.icontent.models.IContentType
import com.orlandev.icontent.utils.toCarouselModelList

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun IContent(contentModel: ContentModel, modifier: Modifier = Modifier.height(200.dp)) {
    when (contentModel.typeI) {
        is IContentType.Text -> {
            IText(
                text = contentModel.field,
                modifier = modifier,
                style = contentModel.textStyle ?: LocalTextStyle.current
            )
        }
        is IContentType.ExtendText -> {
            IExtendText(
                text = contentModel.field,
                modifier = modifier,
                style = contentModel.textStyle ?: LocalTextStyle.current
            )
        }
        is IContentType.Image -> {
            IImageBlur(
                contentModel = contentModel,
                modifier = modifier,
                contentType = contentModel.typeI
            )
        }
        is IContentType.Video -> {
            //TODO(" ADD VIDEO PROPERTIES")
            IVideoPlayer(modifier = modifier, url = contentModel.field)
        }
        is IContentType.Pano -> {
            IPanoView(
                contentModel = contentModel,
                modifier = modifier.fillMaxWidth()
            )
        }

        is IContentType.Carousel -> {
            CarouselContainer(modifier = modifier,
                carouselDataList = contentModel.field.toCarouselModelList(),
                onCarouselItemClick = {})
        }
        is IContentType.BeforeAfter -> {

            ImageBeforeAfter(modifier = modifier, contentModel = contentModel)

        }

        else -> {
            TODO("CONTENT TYPE IS UNDEFINED - TYPE: ${contentModel.typeI}")
        }
    }

}