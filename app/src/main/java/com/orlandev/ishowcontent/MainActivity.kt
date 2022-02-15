package com.orlandev.ishowcontent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
                        typeI = "text".toIContentType(R.drawable.no_image),
                    )
                    val contentPano = IContentModel(
                        field = "url para el pano",
                        typeI = "pano".toIContentType(R.drawable.no_image),
                    )
                    val contentImage = IContentModel(
                        field = "https://blurha.sh/assets/images/img4.jpg[!]LKO2?U%2Tw=w]~RBVZRi};RPxuwH",
                        typeI = "image".toIContentType(R.drawable.no_image),
                    )

                    val contentVideo = IContentModel(
                        field = "https://youtu.be/qvDo0SKR8-k",
                        typeI = "video".toIContentType(R.drawable.no_image),
                    )
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        item {

                            IContent(
                                iContentModel = contentText,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                        item {
                            IContent(
                                iContentModel = contentPano,
                                modifier = Modifier
                                    .fillMaxWidth().height(200.dp)
                                    .padding(20.dp)
                                    .clip(RoundedCornerShape(20.dp))
                            )
                        }
                        /*
                        item {
                            IContent(
                                iContentModel = contentImage,
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
                                    iContentModel = contentVideo,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }*/
                    }
                }
            }
        }
    }
}


