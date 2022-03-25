package com.orlandev.icontent.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle

//TODO - ADD FONTS AND MORE CONFIGURATIONS FOR TEXTS
@Composable
fun IText(
    text: String, modifier: Modifier = Modifier, style: TextStyle
) {
    Text(text = text, modifier = modifier, style = style)
}
