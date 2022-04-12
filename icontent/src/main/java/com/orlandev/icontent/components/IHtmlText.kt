package com.orlandev.icontent.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import de.charlex.compose.HtmlText

@Composable
fun IHtmlText(
    text: String,
    modifier: Modifier = Modifier,
    primaryColor: Color = Color.White,
    style: TextStyle
) {
    HtmlText(
        text = text,
        modifier = modifier,
        color = primaryColor,
        style = style
    )
}