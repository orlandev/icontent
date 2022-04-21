package com.orlandev.icontent.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

//TODO - ADD FONTS AND MORE CONFIGURATIONS FOR TEXTS
@Composable
fun IText(
    text: String, modifier: Modifier = Modifier, style: TextStyle = LocalTextStyle.current
) {
    androidx.compose.material3.Text(
        text = text,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier,
        style = style
    )
}
