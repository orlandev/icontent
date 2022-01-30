package com.orlandev.icontent

import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import com.orlandev.icontent.composables.IImageBlur
import com.orlandev.icontent.composables.IText
import com.orlandev.icontent.models.IContentModel
import com.orlandev.icontent.models.IContentType


@OptIn(ExperimentalCoilApi::class)
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
            TODO("NOT IMPLEMENTED YET")
        }
        is IContentType.Pano -> {
            TODO("NOT IMPLEMENTED YET")
        }
    }

}



