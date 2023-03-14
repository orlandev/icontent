package com.orlandev.icontent.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.orlandev.icontent.utils.smartTruncate

@Composable
fun CardTextInfo(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    titleUppercase: Boolean = false,
    subtitleUppercase: Boolean = false,
    textColor: Color = Color.White,
    textAlignment: TextAlign = TextAlign.Center,
) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = if (titleUppercase) title.uppercase() else title,
            overflow = TextOverflow.Ellipsis,
            textAlign = textAlignment,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            color = textColor
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = if (subtitleUppercase) subtitle.smartTruncate(90)
                .uppercase() else subtitle.smartTruncate(90),
            overflow = TextOverflow.Ellipsis,
            textAlign = textAlignment,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Light,
            maxLines = 2,
            color = textColor,
        )
    }
}



