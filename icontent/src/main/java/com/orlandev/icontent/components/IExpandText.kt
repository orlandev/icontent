package com.orlandev.icontent.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.orlandev.icontent.utils.smartTruncate



/***
 *
 *  [IExtendText] is a component that allows to display a text adding the property of extending it or not in the form of an accordion.
 *  @param text Is a String containing text in HTML format or plain text.
 *  @param modifier Modifiers to control the appearance of the component, such as height, width, padding, etc.
 *  @param maxTextTruncate This parameter controls the amount of text to display, cutting the text to the suggested size and adding ...
 *  @param style Allows you to add a general style to the text
 *
 *  @author Orlando N. Rodriguez
 *
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun IExtendText(
    text: String,
    modifier: Modifier,
    maxTextTruncate: Int = 300,
    style: androidx.compose.ui.text.TextStyle = LocalTextStyle.current,
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
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = if (targetExpanded) text else text.smartTruncate(
                    max = maxTextTruncate
                ),
                color = MaterialTheme.colorScheme.onBackground,
                style = style,
            )
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                if (!expanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                contentDescription = "", tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

