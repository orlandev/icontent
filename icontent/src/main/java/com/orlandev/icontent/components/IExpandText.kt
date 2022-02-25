package com.orlandev.icontent.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.orlandev.icontent.utils.smartTruncate

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun IExtendText(text: String, modifier: Modifier, maxTextTruncate: Int = 300) {
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
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = if (targetExpanded) text else text.smartTruncate(
                    max = maxTextTruncate
                ),
                textAlign = TextAlign.Justify,
            )
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                if (!expanded) Icons.Filled.Add else Icons.Filled.Remove,
                contentDescription = ""
            )
        }
    }
}

