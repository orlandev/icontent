package com.orlandev.icontent.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.RateReview
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewStars(
    siteReview: String,
    shareOption: String,
    comment: String,
    buttonText: String,
    backgrounColor: Color = Color.White,
    onBackgrounColor: Color = Color.Black,
    onCommentTextFieldFocusChanged: (FocusState) -> Unit,
    onUserReview: (Int, String) -> Unit
) {

    var reviewStars by remember {
        mutableStateOf<Int>(0)
    }

    val userReviewComment = remember { mutableStateOf(TextFieldValue()) }

    androidx.compose.material3.Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row {
                Icon(Icons.Rounded.RateReview, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = siteReview,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left
                , color = androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Text(
                text = shareOption,
                style = MaterialTheme.typography.body2
                , color = androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {


                IconButton(onClick = { reviewStars = 1 }) {
                    Icon(
                        if (reviewStars > 0) Icons.Rounded.Star else Icons.Rounded.StarBorder,
                        contentDescription = "Review",
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = { reviewStars = 2 }) {
                    Icon(
                        if (reviewStars > 1) Icons.Rounded.Star else Icons.Rounded.StarBorder,
                        contentDescription = "Review",
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = { reviewStars = 3 }) {
                    Icon(
                        if (reviewStars > 2) Icons.Rounded.Star else Icons.Rounded.StarBorder,
                        contentDescription = "Review",
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = { reviewStars = 4 }) {
                    Icon(
                        if (reviewStars > 3) Icons.Rounded.Star else Icons.Rounded.StarBorder,
                        contentDescription = "Review",
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = { reviewStars = 5 }) {
                    Icon(
                        if (reviewStars > 4) Icons.Rounded.Star else Icons.Rounded.StarBorder,
                        contentDescription = "Review",
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.primary
                    )
                }
            }
            AnimatedVisibility(visible = reviewStars > 0) {

                Column(modifier = Modifier.fillMaxSize()) {
                    val maxReviewChar = 150
                    androidx.compose.material3.OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusChanged {
                                onCommentTextFieldFocusChanged(it)
                            }
                            .padding(horizontal = 8.dp),
                        value = userReviewComment.value,
                        maxLines = 5,
                        label = {
                            Text(
                                text = comment,
                                style = MaterialTheme.typography.body2
                                , color = androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        },
                        onValueChange = {
                            if (it.text.length <= maxReviewChar) userReviewComment.value =
                                it
                        },
                        trailingIcon = {
                            if (userReviewComment.value.text.isNotEmpty()) Icon(
                                Icons.Filled.Close,
                                modifier = Modifier.clickable {
                                    userReviewComment.value = TextFieldValue()
                                },
                                contentDescription = null
                            )
                        }
                    )
                    Text(
                        text = "${userReviewComment.value.text.length} / 150",
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.caption,
                        color = androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp)
                    )

                    androidx.compose.material3.TextButton(
                        enabled = userReviewComment.value.text.isNotEmpty(),
                        onClick = {
                            onUserReview(
                                reviewStars,
                                userReviewComment.value.text
                            )
                        }) {
                        Text(
                            text = buttonText,
                            style = MaterialTheme.typography.button
                            , color = androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
        }
    }
}
