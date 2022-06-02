package com.orlandev.icontent.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

sealed class GradientEffectOrientation() {
    object Vertically : GradientEffectOrientation()
    object Horizontally : GradientEffectOrientation()
}

sealed class GradientAlignment() {
    object Start : GradientAlignment()
    object Center : GradientAlignment()
    object End : GradientAlignment()
}


@Composable
fun IGradientEffect(
    backgroundColor: Color,
    orientation: GradientEffectOrientation = GradientEffectOrientation.Vertically,
    align: GradientAlignment = GradientAlignment.End,
    alphaValue: Float = 0.9f
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                when (orientation) {
                    GradientEffectOrientation.Horizontally -> {
                        Brush.horizontalGradient(
                            colorStops = getGradient(
                                align,
                                backgroundColor.copy(alpha = alphaValue)
                            )
                        )
                    }
                    GradientEffectOrientation.Vertically -> {
                        Brush.verticalGradient(
                            colorStops = getGradient(
                                align,
                                backgroundColor.copy(alpha = alphaValue)
                            )
                        )
                    }
                }
            )
    )
}

fun getGradient(align: GradientAlignment, backgroundColor: Color): Array<Pair<Float, Color>> {
    return when (align) {
        GradientAlignment.Center -> {
            arrayOf(
                Pair(0f, Color.Transparent),
                Pair(0.50f, backgroundColor),
                Pair(1f, Color.Transparent),
            )
        }
        GradientAlignment.Start -> {
            arrayOf(
                Pair(0f, backgroundColor),
                Pair(0.50f, Color.Transparent)
            )
        }
        GradientAlignment.End ->
            arrayOf(
                Pair(0.40f, Color.Transparent),
                Pair(1f, backgroundColor)
            )

    }
}
