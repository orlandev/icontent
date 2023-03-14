package com.orlandev.icontent.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/***
 *  [GradientEffectOrientation]  Gradient Orientation
 *  @param Vertically sets the Gradient vertically.
 *  @param Horizontally sets the Gradient horizontally.
 */
sealed class GradientEffectOrientation {
    object Vertically : GradientEffectOrientation()
    object Horizontally : GradientEffectOrientation()
}


/***
 *  [GradientAlignment]  Gradient Alignment
 *  @param Start Align the gradient to the start of the component.
 *  @param Center Align the gradient to the center of the component.
 *  @param End Align the gradient to the end of the component.
 */
sealed class GradientAlignment {
    object Start : GradientAlignment()
    object Center : GradientAlignment()
    object End : GradientAlignment()
}


/**
 *  [GradientEffect] Create a component with gradient and transparency which can be used on top of an image.
 *  @param backgroundColor Main color of the gradient.
 *  @param orientation Gradient orientation; the default object [GradientAlignment] should be used for orientation.
 *  @param align Gradient alignment within the component; you must use the default object [GradientAlignment] for alignment.
 *  @param alpha Transparency that the gradient will have.
 *
 */
@Composable
fun GradientEffect(
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
