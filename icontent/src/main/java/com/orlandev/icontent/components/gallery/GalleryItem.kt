package com.orlandev.icontent.components.gallery

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.orlandev.icontent.components.IImageBlur
import com.orlandev.icontent.models.ContentUIModel
@Deprecated(message = "This will be deleted in the future versions")
@Composable
fun GalleryItem(
    modifier: Modifier = Modifier,
    imageContent: ContentUIModel,
    contentScale: ContentScale = ContentScale.Crop
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .clickable(
                onClick = { },

                ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(modifier = Modifier.height((50..200).random().dp)) {
            IImageBlur(
                modifier = Modifier
                    .fillMaxSize(),
                contentUIModel = imageContent,
                contentType = imageContent.typeI,
                contentScale = contentScale
            )
        }
    }
}