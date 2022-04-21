package com.orlandev.icontent.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import de.charlex.compose.HtmlText

@Composable
fun IHtmlText(
    text: String,
    modifier: Modifier = Modifier,
    primaryColor: Color =MaterialTheme.colorScheme.onBackground,
    style: TextStyle = LocalTextStyle.current,
) {
    HtmlText(
        text = text,
        modifier = modifier,
        color = primaryColor,
        style = style
    )
}