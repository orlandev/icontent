package com.orlandev.icontent.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.*
import com.ondev.imageblurkt_lib.AsyncImageBlurHash
import com.ondev.imageblurkt_lib.ImageBlurHashModel
import com.qamar.elasticview.ElasticView
import kotlin.math.absoluteValue


@ExperimentalCoilApi
@OptIn(ExperimentalPagerApi::class)
@Composable
fun CardImageSlider(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(10.dp),
    imageList: List<ImageBlurHashModel>,
    contentAlignment: Alignment = Alignment.Center,
    notImageFound: Any,
    onPageClicked: (Int) -> Unit,
    content: (@Composable () -> Unit)? = null
) {

    val pagerState = rememberPagerState()

    ElasticView(onClick = {
        onPageClicked(pagerState.currentPage)
    }) {
        Card(
            modifier = modifier,
            shape = shape,
        ) {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = contentAlignment) {
                HorizontalPager(
                    count = imageList.size, state = pagerState, modifier = Modifier.fillMaxSize()
                ) { page ->

                    Box(modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                            lerp(
                                start = 0.6f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }

                            // We animate the alpha, between 50% and 100%
                            alpha = lerp(
                                start = 0.5f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }) {

                        AsyncImageBlurHash(
                            modifier = modifier,
                            model = imageList[page],
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            notImageFoundRes = notImageFound
                        )
                    }
                }

                content?.let {
                    it()
                }

                if (imageList.size > 1) {

                    HorizontalPagerIndicator(
                        modifier = Modifier
                            .padding(
                                vertical = 16.dp,
                            )
                            .align(Alignment.BottomCenter),
                        pagerState = pagerState,
                        activeColor = Color.White,
                        inactiveColor = Color.Gray,
                        indicatorWidth = 6.dp,
                        indicatorHeight = 6.dp,
                        spacing = 8.dp,
                    )
                }
            }
        }
    }
}

