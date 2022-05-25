package com.orlandev.icontent.components.carousel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.orlandev.icontent.components.ForegroundGradientEffect
import com.orlandev.icontent.components.IImageBlur
import com.orlandev.icontent.models.ContentModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarouselItem(
    modifier: Modifier = Modifier,
    imageContent: ContentModel,
    title: String,
    subtitle: String,
    gradientColor: Color = MaterialTheme.colorScheme.background,
    textColorOverGradient: Color = MaterialTheme.colorScheme.onBackground,
    addGradient: Boolean = false,
    shape: RoundedCornerShape = RoundedCornerShape(0.dp),
) {
    Card(
        shape = shape,
        modifier = modifier
    )
    {
        Box(modifier = Modifier.fillMaxSize()) {
            IImageBlur(
                modifier = Modifier
                    .fillMaxSize(),
                contentModel = imageContent,
                contentType = imageContent.typeI
            )
            if (addGradient)
                ForegroundGradientEffect(backgroundColor = gradientColor)


            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ) {
                Text(text = title, overflow = TextOverflow.Ellipsis)

                Text(
                    text = subtitle,
                    maxLines = 1,
                    color = textColorOverGradient,
                    overflow = TextOverflow.Ellipsis
                )
            }


        }
    }
}