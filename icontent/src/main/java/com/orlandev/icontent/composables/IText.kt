package com.orlandev.icontent.composables

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

//TODO - ADD FONTS AND MORE CONFIGURATIONS FOR TEXTS
@Composable
internal fun IText(
    text: String, modifier: Modifier = Modifier
) {
    Text(text = text, modifier = modifier)
}