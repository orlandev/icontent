package com.orlandev.icontent.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

sealed class GradientEffectOrientation() {
    object VerticalOrientation : GradientEffectOrientation()
    object HorizontalOrientation : GradientEffectOrientation()
}

@Composable
fun ForegroundGradientEffect(
    backgroundColor: Color,
    orientation: GradientEffectOrientation = GradientEffectOrientation.VerticalOrientation
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                when (orientation) {
                    GradientEffectOrientation.HorizontalOrientation -> {
                        Brush.horizontalGradient(
                            colorStops = arrayOf(
                                Pair(0.50f, Color.Transparent),
                                Pair(1.9f, backgroundColor)
                            )
                        )
                    }
                    GradientEffectOrientation.VerticalOrientation -> {
                        Brush.verticalGradient(
                            colorStops = arrayOf(
                                Pair(0.50f, Color.Transparent),
                                Pair(1.9f, backgroundColor)
                            )
                        )
                    }
                }
            )
    )
}