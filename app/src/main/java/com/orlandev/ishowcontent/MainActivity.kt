package com.orlandev.ishowcontent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.orlandev.icontent.IContent
import com.orlandev.icontent.models.IContentModel
import com.orlandev.icontent.models.IContentType
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

                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        item {
                            val content = IContentModel(
                                field = "This is a text",
                                typeI = IContentType.Text,
                            )
                            IContent(IContentModel = content)
                        }
                    }

                }
            }
        }
    }
}
