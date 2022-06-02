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

sealed class GradientAlignment() {
    object Start : GradientAlignment()
    object Center : GradientAlignment()
    object End : GradientAlignment()
}


@Composable
fun IGradientEffect(
    backgroundColor: Color,
    orientation: GradientEffectOrientation = GradientEffectOrientation.VerticalOrientation,
    align: GradientAlignment = GradientAlignment.End,
    alphaValue: Float = 0.9f
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                when (orientation) {
                    GradientEffectOrientation.HorizontalOrientation -> {
                        Brush.horizontalGradient(
                            colorStops = getGradient(align,backgroundColor.copy(alpha = alphaValue))
                        )
                    }
                    GradientEffectOrientation.VerticalOrientation -> {
                        Brush.verticalGradient(
                            colorStops = getGradient(align,backgroundColor.copy(alpha = alphaValue))
                        )
                    }
                }
            )
    )
}

fun getGradient(align: GradientAlignment, backgroundColor: Color): Array<Pair<Float, Color>> {
    return when (align) {
        GradientAlignment.End ->
            arrayOf(
                Pair(0.50f, Color.Transparent),
                Pair(1.9f, backgroundColor)
            )
        GradientAlignment.Center -> {
            arrayOf(
                Pair(0.50f, Color.Transparent),
                Pair(1.9f, backgroundColor),
                Pair(0.50f, Color.Transparent),
            )
        }
        GradientAlignment.Start -> {
            arrayOf(
                Pair(1.9f, backgroundColor),
                Pair(0.50f, Color.Transparent)
            )
        }
    }
}
