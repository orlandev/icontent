package com.orlandev.icontent

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import com.orlandev.icontent.components.*
import com.orlandev.icontent.models.IContentModel
import com.orlandev.icontent.models.IContentType


@OptIn(ExperimentalCoilApi::class, androidx.compose.animation.ExperimentalAnimationApi::class)
@Composable
fun IContent(iContentModel: IContentModel, modifier: Modifier = Modifier) {
    when (iContentModel.typeI) {
        is IContentType.Text -> {
            IText(
                text = iContentModel.field,
                modifier = modifier
            )
        }
        is IContentType.ExtendText -> {
            IExtendText(
                text = iContentModel.field,
                modifier = modifier
            )
        }
        is IContentType.Image -> {
            IImageBlur(
                contentModel = iContentModel,
                modifier = modifier,
                contentType = iContentModel.typeI
            )
        }
        is IContentType.Video -> {
            //TODO(" ADD VIDEO PROPERTIES")
            IVideoPlayer(modifier = modifier, url = iContentModel.field)
        }
        is IContentType.Pano -> {
            IPanoView(contentModel = iContentModel, modifier = modifier)
        }
        IContentType.HtmlText ->
        {
            IHtmlText(modifier = modifier, text = iContentModel.field)
        }

        else -> {
            TODO("CONTENT TYPE IS UNDEFINED - NOT IMPLEMENTED YET")
        }
    }

}



