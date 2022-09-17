package com.orlandev.icontent.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewStars(
    siteReview: String? = null,
    shareOption: String? = null,
    comment: String? = null,
    buttonText: String,
    activeColor: Color,
    titleCardTextStyle: androidx.compose.ui.text.TextStyle,
    subtitleCardTextStyle: androidx.compose.ui.text.TextStyle,
    onCommentTextFieldFocusChanged: (FocusState) -> Unit,
    onUserReview: (Int, String) -> Unit
) {

    val userReviewComment = remember { mutableStateOf(TextFieldValue()) }

    var rating: Float by remember {
        mutableStateOf(0f)
    }

    androidx.compose.material3.ElevatedCard(
        elevation = CardDefaults.cardElevation(
            0.dp,
            0.dp,
            0.dp,
            0.dp,
            0.dp,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            siteReview?.let {
                androidx.compose.material3.Text(
                    text = siteReview,
                    style = titleCardTextStyle,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            shareOption?.let {
                androidx.compose.material3.Text(
                    text = shareOption,
                    style = subtitleCardTextStyle,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground
                )
            }

            RatingBar(
                value = rating,
                config = RatingBarConfig()
                    .activeColor(activeColor)
                    .hideInactiveStars(false)
                    .inactiveColor(Color.LightGray)
                    .stepSize(StepSize.ONE)
                    .numStars(5)
                    //.isIndicator(true)
                    .size(24.dp)
                    .padding(16.dp)
                    .style(RatingBarStyle.Normal),
                onValueChange = {
                    rating = it
                },
                onRatingChanged = {
                    Log.d("TAG", "onRatingChanged: $it")
                }
            )

            AnimatedVisibility(visible = rating > 0) {

                Column(modifier = Modifier.fillMaxSize()) {
                    val maxReviewChar = 150
                    androidx.compose.material3.OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusChanged {
                                onCommentTextFieldFocusChanged(it)
                            },
                        value = userReviewComment.value,
                        maxLines = 5,
                        label = {
                            comment?.let {
                                Text(
                                    text = comment,
                                    style = MaterialTheme.typography.body2,
                                    color = androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
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
                        color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.End,
                        style = androidx.compose.material3.MaterialTheme.typography.labelSmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, end = 16.dp)
                    )

                    androidx.compose.material3.TextButton(
                        enabled = userReviewComment.value.text.isNotEmpty(),
                        onClick = {
                            onUserReview(
                                rating.toInt(),
                                userReviewComment.value.text
                            )
                        }) {
                        Text(
                            text = buttonText,
                            style = MaterialTheme.typography.button,
                            color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    }
}
