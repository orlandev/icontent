package com.orlandev.icontent.components.carousel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.orlandev.icontent.components.IImageBlur
import com.orlandev.icontent.models.ContentModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarouselItem(
    modifier: Modifier = Modifier,
    imageContent: ContentModel,
    title: String,
    subtitle: String,
) {
    Card(
        shape = RoundedCornerShape(30.dp),
        modifier = modifier
    )
    {
        Box(modifier = Modifier.fillMaxSize()) {
            IImageBlur(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter),
                contentModel = imageContent,
                contentType = imageContent.typeI
            )
            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                Text(text = title)
                Text(text = subtitle)
            }
        }
    }
}