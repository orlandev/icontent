package com.orlandev.icontent

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.annotation.ExperimentalCoilApi
import com.ondev.imageblurkt_lib.ImageBlur
import com.orlandev.icontent.composables.IImageBlur
import com.orlandev.icontent.composables.IText
import com.orlandev.icontent.models.IContentModel
import com.orlandev.icontent.models.IContentType
import com.orlandev.icontent.utils.FIELD_IMAGE_BLUR_DELIMITIER


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
            IImageBlur(IContentModel.field, modifier, IContentModel.typeI.noImageFound)
        }
        is IContentType.Video -> {
            TODO("NOT IMPLEMENTED YET")
        }
        is IContentType.Pano -> {
            TODO("NOT IMPLEMENTED YET")
        }
    }
}



