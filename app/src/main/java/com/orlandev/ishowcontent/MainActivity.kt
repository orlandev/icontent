package com.orlandev.ishowcontent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.orlandev.icontent.IContent
import com.orlandev.icontent.models.IContentModel
import com.orlandev.icontent.utils.toIContentType
import com.orlandev.ishowcontent.ui.theme.IShowContentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IShowContentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val contentText = IContentModel(
                        field = "This is a text using IContent",
                        typeI = "text".toIContentType(LocalContext.current, R.drawable.no_image),
                    )
                    val contentImage = IContentModel(
                        field = "https://blurha.sh/assets/images/img4.jpg[!]LKO2?U%2Tw=w]~RBVZRi};RPxuwH",
                        typeI = "image".toIContentType(LocalContext.current, R.drawable.no_image),
                    )

                    val contentVideo = IContentModel(
                        field = "https://youtu.be/qvDo0SKR8-k",
                        typeI = "video".toIContentType(LocalContext.current, R.drawable.no_image),
                    )
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        item {

                            IContent(
                                IContentModel = contentText,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                        item {
                            IContent(
                                IContentModel = contentImage,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                        }
                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            ) {
                                IContent(
                                    IContentModel = contentVideo,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


