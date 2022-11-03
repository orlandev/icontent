package com.orlandev.icontent.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import de.charlex.compose.HtmlText
import dev.jeziellago.compose.markdowntext.MarkdownText

/**
 * [IHtmlText] Allows to display text in html format
 * @param text String value
 * @param modifier  Modifiers to control the appearance of the component, such as height, width, padding, etc.
 * @param primaryColor Primary color for the text.
 * @param style Style for the text.
 */

@Composable
fun IHtmlText(
    text: String,
    modifier: Modifier = Modifier,
    primaryColor: Color =MaterialTheme.colorScheme.onBackground,
    style: TextStyle = LocalTextStyle.current,
) {

    MarkdownText(
        modifier=modifier,
        markdown = text,
        style = style
    )

    /*HtmlText(
        text = text,
        modifier = modifier,
        color = primaryColor,
        style = style
    )*/
}