package com.orlandev.icontent.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.orlandev.icontent.utils.smartTruncate

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun IExtendHtmlText(
    text: String,
    modifier: Modifier,
    maxTextTruncate: Int = 300,
    style: TextStyle = LocalTextStyle.current,
    iconTint: Color = MaterialTheme.colorScheme.onBackground
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier, horizontalAlignment = Alignment.End) {
        AnimatedContent(
            modifier = Modifier.fillMaxWidth(),
            targetState = expanded,
            transitionSpec = {
                // Compare the incoming number with the previous number.
                if (expanded) {
                    // If the target number is larger, it slides up and fades in
                    // while the initial (smaller) number slides up and fades out.
                    fadeIn() with
                            fadeOut()
                } else {
                    // If the target number is smaller, it slides down and fades in
                    // while the initial number slides down and fades out.
                    fadeIn() with
                            fadeOut()
                }.using(
                    // Disable clipping since the faded slide-in/out should
                    // be displayed out of bounds.
                    SizeTransform(clip = false)
                )
            }
        ) { targetExpanded ->
            IHtmlText(
                modifier = Modifier.fillMaxWidth(),
                text = if (targetExpanded) text else text.smartTruncate(
                    max = maxTextTruncate
                ),
                primaryColor = MaterialTheme.colorScheme.onBackground,
                style = style
            )
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                if (!expanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                contentDescription = "",
                tint = iconTint
            )
        }
    }
}
