package com.orlandev.icontent.components

import androidx.compose.material.Text
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle

//TODO - ADD FONTS AND MORE CONFIGURATIONS FOR TEXTS
@Composable
fun IText(
    text: String, modifier: Modifier = Modifier, style: TextStyle = LocalTextStyle.current
) {
    androidx.compose.material3.Text(text = text, modifier = modifier, style = style)
}
