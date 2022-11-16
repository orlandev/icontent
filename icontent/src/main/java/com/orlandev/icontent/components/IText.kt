package com.orlandev.icontent.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import dev.jeziellago.compose.markdowntext.MarkdownText

//TODO - ADD FONTS AND MORE CONFIGURATIONS FOR TEXTS
@Composable
fun IText(
    text: String, modifier: Modifier = Modifier, style: TextStyle = LocalTextStyle.current
) {

    MarkdownText(
        markdown = text, modifier = modifier, style = style
    )

}
