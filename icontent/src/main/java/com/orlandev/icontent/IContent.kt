package com.orlandev.icontent

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.orlandev.icontent.components.*
import com.orlandev.icontent.components.carousel.CarouselContainer
import com.orlandev.icontent.models.ContentModel
import com.orlandev.icontent.models.IContentType
import com.orlandev.icontent.utils.toCarouselModelList


@OptIn(ExperimentalCoilApi::class, androidx.compose.animation.ExperimentalAnimationApi::class)
@Composable
fun IContent(contentModel: ContentModel, modifier: Modifier = Modifier) {
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
            IPanoView(contentModel = contentModel, modifier = modifier)
        }
        IContentType.HtmlText -> {
            IHtmlText(
                text = contentModel.field,
                modifier = modifier,
                style = contentModel.textStyle ?: LocalTextStyle.current
            )
        }

        IContentType.ExtendHtmlText -> {
            IExtendHtmlText(
                modifier = modifier,
                text = contentModel.field,
                style = contentModel.textStyle ?: LocalTextStyle.current
            )
        }
        IContentType.Carousel -> {
            CarouselContainer(
                modifier = Modifier
                    .fillMaxSize(),
                carouselDataList = contentModel.field.toCarouselModelList(),
                onCarouselItemClick = {})
        }
        else -> {
            TODO("CONTENT TYPE IS UNDEFINED - NOT IMPLEMENTED YET")
        }
    }

}



