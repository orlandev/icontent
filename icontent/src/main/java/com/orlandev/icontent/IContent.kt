package com.orlandev.icontent

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import com.orlandev.icontent.components.IImageBlur
import com.orlandev.icontent.components.IPanoView
import com.orlandev.icontent.components.IText
import com.orlandev.icontent.components.IVideoPlayer
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
        is IContentType.Image -> {
            IImageBlur(iContentModel.field, modifier, iContentModel.typeI)
        }
        is IContentType.Video -> {
            //TODO(" ADD VIDEO PROPERTIES")
            IVideoPlayer(modifier = modifier, url = iContentModel.field)
        }
        is IContentType.Pano -> {
            IPanoView(field = iContentModel.field, modifier = modifier)
        }
        else -> {
            TODO("NOT IMPLEMENTED YET")
        }
    }

}



