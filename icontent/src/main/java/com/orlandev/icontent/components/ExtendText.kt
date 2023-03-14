package com.orlandev.icontent.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orlandev.icontent.utils.smartTruncate


/***
 *
 *  [ExtendText] is a component that allows to display a text adding the property of extending it or not in the form of an accordion.
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
fun ExtendText(
    text: String,
    modifier: Modifier,
    maxTextTruncate: Int = 300,
    style: androidx.compose.ui.text.TextStyle = LocalTextStyle.current,
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {

        AnimatedContent(modifier = Modifier
            .fillMaxWidth()
            .weight(8f),
            targetState = expanded,
            transitionSpec = {
                // Compare the incoming number with the previous number.
                if (expanded) {
                    // If the target number is larger, it slides up and fades in
                    // while the initial (smaller) number slides up and fades out.
                    fadeIn() with fadeOut()
                } else {
                    // If the target number is smaller, it slides down and fades in
                    // while the initial number slides down and fades out.
                    fadeIn() with fadeOut()
                }.using(
                    // Disable clipping since the faded slide-in/out should
                    // be displayed out of bounds.
                    SizeTransform(clip = false)
                )
            }) { targetExpanded ->
            HtmlText(
                modifier = Modifier.fillMaxWidth(),
                text = if (targetExpanded) text else text.smartTruncate(
                    max = maxTextTruncate
                ),
                style = style,
            )
        }

        IconButton(modifier = Modifier.weight(1f), onClick = { expanded = !expanded }) {
            Icon(
                if (!expanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview
@Composable
fun ExtendTextPreview() {
    MaterialTheme {
        ExtendText(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp), text = """
                Lorem Ipsum is simply dummy text of the printing and typesetting industry.
                 Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
                 when an unknown printer took a galley of type and scrambled it to make a type specimen book.
                  It has survived not only five centuries, but also the leap into electronic typesetting, remaining 
                  essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing 
                  Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including
                   versions of Lorem Ipsum.
                """
        )
    }
}


