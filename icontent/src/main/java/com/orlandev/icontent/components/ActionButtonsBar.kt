package com.orlandev.icontent.components

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orlandev.icontent.R


sealed class ActionButtonUiEvent {
    object NavigateToMap : ActionButtonUiEvent()
    data class TextToSpeech(val active: Boolean) : ActionButtonUiEvent()
    object Share : ActionButtonUiEvent()
    object OpenUrl : ActionButtonUiEvent()
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ActionButtonsBar(
    urlEnable: Boolean = true,
    strokeColor: Color,
    onEvent: (ActionButtonUiEvent) -> Unit
) {
    Card(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            //GoToMap
            Button(
                onClick = {
                    onEvent(ActionButtonUiEvent.NavigateToMap)
                },
                modifier = Modifier.size(50.dp),  //avoid the oval shape
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = strokeColor,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    painterResource(R.drawable.ic_baseline_directions_24),
                    contentDescription = stringResource(id = R.string.navigate_to_map)
                )
            }
            //Speaker
            var isSelected by remember { mutableStateOf(false) }
            OutlinedButton(
                onClick = {
                    isSelected = !isSelected
                    onEvent(ActionButtonUiEvent.TextToSpeech(isSelected))
                },
                modifier = Modifier.size(50.dp),  //avoid the oval shape
                shape = CircleShape,
                border = BorderStroke(1.dp, strokeColor),
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.outlinedButtonColors(contentColor = strokeColor)
            ) {
                AnimatedContent(
                    targetState = isSelected,
                    transitionSpec = {
                        // Compare the incoming number with the previous number.
                        if (isSelected) {
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
                ) { targetIsSelected ->
                    Icon(
                        painterResource(
                            if (!targetIsSelected) R.drawable.ic_baseline_hearing_24 else R.drawable.ic_baseline_hearing_disabled_24
                        ),
                        contentDescription = stringResource(id = R.string.speach_btn)
                    )
                }
            }

            //ShareButton
            OutlinedButton(
                onClick = {
                    onEvent(ActionButtonUiEvent.Share)
                },
                modifier = Modifier.size(50.dp),  //avoid the oval shape
                shape = CircleShape,
                border = BorderStroke(1.dp, strokeColor),
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.outlinedButtonColors(contentColor = strokeColor)
            ) {
                Icon(
                    Icons.Filled.Share,
                    contentDescription = stringResource(id = R.string.share)
                )
            }
            //Goto URL
            OutlinedButton(
                enabled = urlEnable,
                onClick = {
                    onEvent(ActionButtonUiEvent.OpenUrl)
                },
                modifier = Modifier.size(50.dp),  //avoid the oval shape
                shape = CircleShape,
                border = BorderStroke(1.dp, strokeColor),
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.outlinedButtonColors(contentColor = strokeColor)
            ) {
                Icon(
                    painterResource(R.drawable.ic_button_link_24),
                    contentDescription = stringResource(id = R.string.open_url)
                )
            }
        }
    }
}