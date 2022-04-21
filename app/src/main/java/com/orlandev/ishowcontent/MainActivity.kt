package com.orlandev.ishowcontent

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.orlandev.icontent.IContent
import com.orlandev.icontent.components.ActionButtonUiEvent
import com.orlandev.icontent.components.ActionButtonsBar
import com.orlandev.icontent.components.MarqueeText
import com.orlandev.icontent.components.ReviewStars
import com.orlandev.icontent.models.ContentModel
import com.orlandev.icontent.models.IContentType
import com.orlandev.icontent.utils.generateImageContentField
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
                    color = androidx.compose.material3.MaterialTheme.colorScheme.background
                ) {
                    val contentText = ContentModel(
                        field = "This is a text using IContent",
                        typeI = IContentType.Text,
                        textStyle = MaterialTheme.typography.h1
                    )
                    val contentHtmlText = ContentModel(
                        field = "Hello <b>World</b>. This <i><strike>text</strike>sentence</i> is form<b>att<u>ed</u></b> in simple html. <a href=\"https://github.com/orlandev/icontent\">IContent compoenent </a>",
                        typeI = IContentType.HtmlText,
                    )

                    val contentTextExpandable = ContentModel(
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
                        typeI = IContentType.ExtendText,
                    )

                    val contentHtmlTextExpandable = ContentModel(
                        field = """ 
                        Qu'est-ce <b>que</b> le Lorem Ipsum? 
                        Le Lorem Ipsum est simplement du faux texte employé 
                        dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux 
                        texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme 
                        assembla ensemble des morceaux de texte pour réaliser un livre spécimen de polices de 
                        texte. Il n'a pas fait <strong> que survivre cinq siècles, mais s'est aussi adapté à la bureautique 
                        informatique, sans que son contenu n'en soit modifié. Il a été popularisé dans les années 
                        1960 grâce à la vente de feuilles</strong> Letraset contenant des passages du Lorem Ipsum, et, plus 
                        récemment, par son <i>inclusion dans des applications de mise en page de texte</i>, comme Aldus 
                        PageMaker. """
                            .trimIndent(),
                        typeI = IContentType.ExtendHtmlText,
                    )

                    val contentPano =
                        "https://cdn.pixabay.com/photo/2017/06/08/15/39/winter-2383930_960_720.jpg".generateImageContentField(
                            blurHash = "|JED*ptRVsD%V[xt%0t6j[03o#RPM{RPoeodoeaf8^o#R%xZkBRjR.WDa#wsM_xu%Lx[kCM}RkfkI8Mxx]xutQj[V@ayoL%%t7RjRlMxRjs,ocoJs;xaRkRlR-bIs:oJWBROadbIoetRt7j]kCkDRNR*t7adadf5WCflj[",
                            type = IContentType.Pano
                        ).copy(
                            contextActivity = context
                        )

                    val contentImage =
                        "https://blurha.sh/assets/images/img4.jpg".generateImageContentField("LKO2?U%2Tw=w]~RBVZRi};RPxuwH")
                            .copy(noImageFound = R.drawable.no_image)


                    val testUrl =
                        "https://blurha.sh/assets/images/img4.jpg[!]LKO2?U%2Tw=w]~RBVZRi};RPxuwH[!]https://blurha.sh/assets/images/img4.jpg[!]LKO2?U%2Tw=w]~RBVZRi};RPxuwH[!]https://blurha.sh/assets/images/img4.jpg[!]LKO2?U%2Tw=w]~RBVZRi};RPxuwH[!]https://blurha.sh/assets/images/img4.jpg[!]LKO2?U%2Tw=w]~RBVZRi};RPxuwH[!]https://blurha.sh/assets/images/img4.jpg[!]LKO2?U%2Tw=w]~RBVZRi};RPxuwH[!]https://blurha.sh/assets/images/img4.jpg[!]LKO2?U%2Tw=w]~RBVZRi};RPxuwH[!]"
                    val carouselContent = ContentModel(
                        field = testUrl,
                        typeI = IContentType.Carousel
                    )

                    val contentVideo = ContentModel(
                        field = "https://youtu.be/qvDo0SKR8-k",
                        typeI = IContentType.Video,
                    )

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        item {
                            MarqueeText(
                                text = "This is a marquee text kfjwef klw flwkfj lkwef klwef lkwjef lkwfej werw erwekrj werlkjwe rlkwejr wjr wejrwjrwljrw ljr wlkejr w",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp),
                                overflow = TextOverflow.Ellipsis,
                                gradientEdgeColor = androidx.compose.material3.MaterialTheme.colorScheme.background.copy(alpha = 0.9f)
                            )

                        }

                        item {
                            ReviewStars(
                                siteReview = "Califica este sitio",
                                shareOption = "Comparte tu opinion con otros usuarios",
                                comment = "Deja tu comentario...",
                                buttonText = "Publicar",
                                onCommentTextFieldFocusChanged = {},
                                onUserReview = { stars, comment ->
                                    Log.d("Review", "$stars - $comment")

                                }
                            )
                        }

                        item {
                            ActionButtonsBar(strokeColor = Color.Red) { btnEvents ->
                                when (btnEvents) {
                                    ActionButtonUiEvent.NavigateToMap -> {
                                        Log.d("NavigateToMap", "NavigateToMap Event")
                                    }
                                    ActionButtonUiEvent.OpenUrl -> {
                                        Log.d("OpenUrl", "OpenUrl Event")
                                    }

                                    ActionButtonUiEvent.Share -> {
                                        Log.d("Share", "Share Event")
                                    }
                                    is ActionButtonUiEvent.TextToSpeech -> {
                                        Log.d("TextToSpeech", "TextToSpeech Event")
                                    }
                                }
                            }
                        }
                        item {
                            IContent(
                                contentModel = contentText,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                        item {
                            IContent(
                                contentModel = contentHtmlTextExpandable,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                        item {
                            IContent(
                                contentModel = contentHtmlTextExpandable,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }

                        item {
                            IContent(
                                contentModel = contentHtmlText,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }

                        item {
                            IContent(
                                contentModel = contentTextExpandable,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                        item {
                            Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                                IContent(
                                    contentModel = carouselContent,
                                    modifier = Modifier
                                        .fillMaxSize()
                                )
                            }
                        }
                        item {
                            Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                                IContent(
                                    contentModel = carouselContent,
                                    modifier = Modifier
                                        .fillMaxSize()
                                )
                            }
                        }
                        item {

                            IContent(
                                contentModel = contentPano,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                        }
                        item {

                            IContent(
                                contentModel = contentImage,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                        }
                        item {
                            IContent(
                                contentModel = contentVideo,
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


