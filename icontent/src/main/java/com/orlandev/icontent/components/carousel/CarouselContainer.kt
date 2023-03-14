package com.orlandev.icontent.components.carousel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Deprecated(message = "This will be deleted in the future versions")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarouselContainer(
    modifier: Modifier = Modifier,
    carouselDataList: List<CarouselModel>,
    itemsWidth: Dp = 300.dp,
    shape: RoundedCornerShape = RoundedCornerShape(0.dp),
    addGradient: Boolean = false,
    gradientEffectColor: Color = MaterialTheme.colorScheme.background,
    textColorOverGradient: Color = MaterialTheme.colorScheme.onBackground,
    showImageInDialog: Boolean = true,
    onCarouselItemClick: (Int) -> Unit //Id for know what item was clicked
) {
    val cardElevation = CardDefaults.cardElevation(
        0.dp,
        0.dp,
        0.dp,
        0.dp,
        0.dp,
    )

    val imageToShow = rememberSaveable {
        mutableStateOf<CarouselModel?>(null)
    }

    ElevatedCard(
        modifier = modifier.background(Color.Transparent),
        elevation = cardElevation,
        shape = shape
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (carouselDataList.size > 1) {
                LazyRow(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(carouselDataList) { currentCarouselItem ->
                        CarouselItem(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(itemsWidth)
                                .clickable {
                                    //If the ID is -1 no need use click feature
                                    if (!showImageInDialog) {
                                        if (currentCarouselItem.id != -1) {
                                            onCarouselItemClick(currentCarouselItem.id)
                                        }
                                    } else {
                                        imageToShow.value = currentCarouselItem
                                    }
                                },
                            title = currentCarouselItem.title,
                            subtitle = currentCarouselItem.subtitle,
                            imageContent = currentCarouselItem.image,
                            gradientColor = gradientEffectColor,
                            textColorOverGradient = textColorOverGradient,
                            addGradient = addGradient
                        )
                    }
                }
            } else if (carouselDataList.size == 1) {
                CarouselItem(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            //If the ID is -1 no need use click feature
                            if (!showImageInDialog) {
                                if (carouselDataList[0].id != -1) {
                                    onCarouselItemClick(carouselDataList[0].id)
                                }
                            } else {
                                imageToShow.value = carouselDataList[0]
                            }
                        },
                    title = carouselDataList[0].title,
                    subtitle = carouselDataList[0].subtitle,
                    imageContent = carouselDataList[0].image,
                    addGradient = addGradient,
                    gradientColor = gradientEffectColor,
                    textColorOverGradient = textColorOverGradient,
                )
            }
            if (imageToShow.value != null)
                imageToShow.value?.let { currentContentModel ->
                    Dialog(onDismissRequest = { imageToShow.value = null }) {
                        CarouselItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp)
                                .clickable {
                                    imageToShow.value = null
                                },
                            title = currentContentModel.title,
                            subtitle = currentContentModel.subtitle,
                            imageContent = currentContentModel.image,
                            addGradient = addGradient,
                            gradientColor = gradientEffectColor,
                            textColorOverGradient = textColorOverGradient,
                            contentScale = ContentScale.FillWidth
                        )
                    }
                }

        }
    }
}
