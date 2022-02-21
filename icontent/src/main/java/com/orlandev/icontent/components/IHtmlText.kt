package com.orlandev.icontent.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.charlex.compose.HtmlText

@Composable
fun IHtmlText( text: String, modifier: Modifier = Modifier) {
    HtmlText(
        text=text,
        modifier = modifier,
    )
}