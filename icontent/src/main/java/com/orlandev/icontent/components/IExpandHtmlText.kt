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


/***
 *
 *  [IExtendHtmlText] is a component that allows to display a text with HTML format and adding the property of extending it or not in the form of an accordion.
 *  @param text Is a String containing text in HTML format or plain text.
 *  @param modifier Modifiers to control the appearance of the component, such as height, width, padding, etc.
 *  @param maxTextTruncate This parameter controls the amount of text to display, cutting the text to the suggested size and adding ...
 *  @param style Allows you to add a general style to the text
 *  @param iconTint Allows you to set the tint color that the icon will take to extend the text.
 *
 *  @author Orlando N. Rodriguez
 *
 */

@Deprecated("IT WILL NOT BE USED ANYMORE BECAUSE A SINGLE TEXT COMPONENT WILL BE CREATED IN WHICH ALL THE INFORMATION IS PROCESSED BASED ON TEXT USING SEVERAL FORMATS SUCH AS HTML AND MARKDOWN")
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
