package com.orlandev.icontent

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import com.orlandev.icontent.composables.IImageBlur
import com.orlandev.icontent.composables.IText
import com.orlandev.icontent.composables.IVideoPlayer
import com.orlandev.icontent.models.IContentModel
import com.orlandev.icontent.models.IContentType


@OptIn(ExperimentalCoilApi::class, androidx.compose.animation.ExperimentalAnimationApi::class)
@Composable
fun IContent(IContentModel: IContentModel, modifier: Modifier = Modifier) {
    when (IContentModel.typeI) {
        is IContentType.Text -> {
            IText(
                text = IContentModel.field,
                modifier = modifier
            )
        }
        is IContentType.Image -> {
            IImageBlur(IContentModel.field, modifier, IContentModel.typeI)
        }
        is IContentType.Video -> {
            //TODO(" ADD VIDEO PROPERTIES")
            IVideoPlayer(modifier = modifier, url = IContentModel.field)
        }
        is IContentType.Pano -> {
            TODO("NOT IMPLEMENTED YET")
        }
        else -> {
            TODO("NOT IMPLEMENTED YET")
        }
    }

}



