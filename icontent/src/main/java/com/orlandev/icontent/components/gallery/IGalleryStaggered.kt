package com.orlandev.icontent.components.gallery

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orlandev.icontent.components.StaggeredVerticalGrid
import com.orlandev.icontent.models.ContentModel

@Composable
fun IGalleryStaggered(
    modifier: Modifier = Modifier,
    columnWidth: Dp = 190.dp,
    imageContentList: List<ContentModel>
) {
    StaggeredVerticalGrid(
        maxColumnWidth = columnWidth,
        modifier = modifier.padding(4.dp)
    ) {
        imageContentList.forEach {
            GalleryItem(imageContent = it)
        }
    }
}
