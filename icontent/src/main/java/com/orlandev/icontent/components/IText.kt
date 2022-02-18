package com.orlandev.icontent.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

//TODO - ADD FONTS AND MORE CONFIGURATIONS FOR TEXTS
@Composable
fun IText(
    text: String, modifier: Modifier = Modifier
) {
    Text(text = text, modifier = modifier)
}
