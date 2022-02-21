package com.orlandev.ishowcontent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.orlandev.icontent.IContent
import com.orlandev.icontent.models.IContentModel
import com.orlandev.icontent.utils.toIContentType
import com.orlandev.ishowcontent.ui.theme.IShowContentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this
        setContent {
            IShowContentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val contentText = IContentModel(
                        field = "This is a text using IContent",
                        typeI = "text".toIContentType(),
                    )
                    val contentHtmlText = IContentModel(
                        field = "Hello <b>World</b>. This <i><strike>text</strike>sentence</i> is form<b>att<u>ed</u></b> in simple html. <a href=\"https://github.com/orlandev/icontent\">IContent compoenent </a>",
                        typeI = "htmlText".toIContentType(),
                    )

                    val contentTextExpandable = IContentModel(
                        field = """ 
                        Qu'est-ce que le Lorem Ipsum? 
                        Le Lorem Ipsum est simplement du faux texte employé 
                        dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux 
                        texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme 
                        assembla ensemble des morceaux de texte pour réaliser un livre spécimen de polices de 
                        texte. Il n'a pas fait que survivre cinq siècles, mais s'est aussi adapté à la bureautique 
                        informatique, sans que son contenu n'en soit modifié. Il a été popularisé dans les années 
                        1960 grâce à la vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus 
                        récemment, par son inclusion dans des applications de mise en page de texte, comme Aldus 
                        PageMaker. """
                            .trimIndent(),
                        typeI = "extendtext".toIContentType(),
                    )
                    val contentPano = IContentModel(
                        field = "https://cdn.pixabay.com/photo/2017/06/08/15/39/winter-2383930_960_720.jpg[!]|JED*ptRVsD%V[xt%0t6j[03o#RPM{RPoeodoeaf8^o#R%xZkBRjR.WDa#wsM_xu%Lx[kCM}RkfkI8Mxx]xutQj[V@ayoL%%t7RjRlMxRjs,ocoJs;xaRkRlR-bIs:oJWBROadbIoetRt7j]kCkDRNR*t7adadf5WCflj[",
                        typeI = "pano".toIContentType(),
                        contextActivity = context
                    )
                    val contentImage = IContentModel(
                        field = "https://blurha.sh/assets/images/img4.jpg[!]LKO2?U%2Tw=w]~RBVZRi};RPxuwH",
                        typeI = "image".toIContentType(),
                        noImageFound = R.drawable.no_image
                    )

                    val contentVideo = IContentModel(
                        field = "https://youtu.be/qvDo0SKR8-k",
                        typeI = "video".toIContentType(),
                    )


                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            IContent(
                                iContentModel = contentText,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                        item {
                            IContent(
                                iContentModel = contentHtmlText,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }

                        item {
                            IContent(
                                iContentModel = contentTextExpandable,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                        item {

                            IContent(
                                iContentModel = contentPano,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                        }
                        item {

                            IContent(
                                iContentModel = contentImage,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                        }
                        item {
                            IContent(
                                iContentModel = contentVideo,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                        }

                    }
                }
            }
        }
    }
}


