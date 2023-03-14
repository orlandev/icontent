package com.orlandev.ishowcontent

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.ondev.imageblurkt_lib.ImageBlurHashModel
import com.orlandev.icontent.ContentUI
import com.orlandev.icontent.components.*
import com.orlandev.icontent.components.cards.CardImageSlider
import com.orlandev.icontent.components.cards.CardTextInfo
import com.orlandev.icontent.components.carousel.CarouselContainer
import com.orlandev.icontent.components.carousel.CarouselModel
import com.orlandev.icontent.components.gallery.IGalleryStaggered
import com.orlandev.icontent.models.ContentUIModel
import com.orlandev.icontent.models.ContentUIType
import com.orlandev.icontent.utils.generateImageContentField
import com.orlandev.icontent.utils.toCarouselModelList
import com.orlandev.ishowcontent.ui.theme.IShowContentTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class, ExperimentalCoilApi::class)
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
                    val contentText = ContentUIModel(
                        field = "This is a text using IContent",
                        typeI = ContentUIType.fromString("text"),
                        textStyle = androidx.compose.material3.MaterialTheme.typography.displayMedium
                    )
                    val contentHtmlText = ContentUIModel(
                        field = "Hello <b>World</b>. This <i><strike>text</strike>sentence</i> is form<b>att<u>ed</u></b> in simple html. <a href=\"https://github.com/orlandev/icontent\">IContent compoenent </a>",
                        typeI = ContentUIType.fromString("text"),
                    )

                    val contentTextExpandable = ContentUIModel(
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
                        PageMaker. """.trimIndent(),
                        typeI = ContentUIType.fromString("extendText"),
                    )

                    val contentHtmlTextExpandable = ContentUIModel(
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
                        PageMaker. """.trimIndent(),
                        typeI = ContentUIType.fromString("extendText"),
                    )

                    val contentPano =
                        "https://ik.imagekit.io/6xgh00mrhaz/fixed_a1diPygsA.jpg".generateImageContentField(
                            blurHash = "|JED*ptRVsD%V[xt%0t6j[03o#RPM{RPoeodoeaf8^o#R%xZkBRjR.WDa#wsM_xu%Lx[kCM}RkfkI8Mxx]xutQj[V@ayoL%%t7RjRlMxRjs,ocoJs;xaRkRlR-bIs:oJWBROadbIoetRt7j]kCkDRNR*t7adadf5WCflj[",
                            type = ContentUIType.fromString("pano")
                        )

                    val contentImage =
                        "https://ik.imagekit.io/6xgh00mrhaz/before_M6xdpfY7N.jpg".generateImageContentField(
                            "LKO2?U%2Tw=w]~RBVZRi};RPxuwH"
                        ).copy(noImageFound = R.drawable.no_image)


                    val testUrl =
                        "https://ik.imagekit.io/6xgh00mrhaz/before_M6xdpfY7N.jpg[!]LKO2?U%2Tw=w]~RBVZRi};RPxuwH[!]https://ik.imagekit.io/6xgh00mrhaz/before_M6xdpfY7N.jpg[!]LKO2?U%2Tw=w]~RBVZRi};RPxuwH[!]https://ik.imagekit.io/6xgh00mrhaz/before_M6xdpfY7N.jpg[!]LKO2?U%2Tw=w]~RBVZRi};RPxuwH[!]https://ik.imagekit.io/6xgh00mrhaz/before_M6xdpfY7N.jpg[!]LKO2?U%2Tw=w]~RBVZRi};RPxuwH[!]https://ik.imagekit.io/6xgh00mrhaz/before_M6xdpfY7N.jpg[!]LKO2?U%2Tw=w]~RBVZRi};RPxuwH[!]https://ik.imagekit.io/6xgh00mrhaz/before_M6xdpfY7N.jpg[!]LKO2?U%2Tw=w]~RBVZRi};RPxuwH[!]"


                    val carouselWithText = listOf<CarouselModel>(
                        CarouselModel(
                            id = 0,
                            image = contentImage,
                            title = "This is the title",
                            subtitle = "This is the subtitle",
                        ),
                        CarouselModel(
                            id = 1,
                            image = contentImage,
                            title = "This is the title",
                            subtitle = "This is the subtitle",
                        ),
                        CarouselModel(
                            id = 2,
                            image = contentImage,
                            title = "This is the title",
                            subtitle = "This is the subtitle",
                        ),
                        CarouselModel(
                            id = 3,
                            image = contentImage,
                            title = "This is the title",
                            subtitle = "This is the subtitle",
                        ),
                        CarouselModel(
                            id = 4,
                            image = contentImage,
                            title = "This is the title",
                            subtitle = "This is the subtitle",
                        ),
                        CarouselModel(
                            id = 5,
                            image = contentImage,
                            title = "This is the title",
                            subtitle = "This is the subtitle",
                        ),
                    )

                    val carouselContent = ContentUIModel(
                        field = testUrl, typeI = ContentUIType.fromString("carousel")
                    )

                    val contentVideo = ContentUIModel(
                        field = "https://www.youtube.com/watch?v=qvDo0SKR8-k",
                        typeI = ContentUIType.fromString("video"),
                    )

                    val listImages = testUrl.toCarouselModelList().map { it.image }
                    var clicked by remember {
                        mutableStateOf("")
                    }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {

                        val contentBeforeAfter = ContentUIModel(
                            field = "https://ik.imagekit.io/6xgh00mrhaz/before_M6xdpfY7N.jpg[!]https://ik.imagekit.io/6xgh00mrhaz/after_3uqabisEN.jpg",
                            typeI = ContentUIType.fromString("beforeAfter"),
                        )


                        item {
                            ImageBeforeAfter(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(4 / 3f),
                                contentUIModel = contentBeforeAfter,
                            )
                        }

                        item {
                            CardImageSlider(modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                                imageList = listOf(
                                    ImageBlurHashModel(
                                        blurHash = "@#$%TYU",
                                        data = com.orlandev.icontent.R.drawable.img
                                    ),
                                    ImageBlurHashModel(
                                        blurHash = "@#$%TYU",
                                        data = com.orlandev.icontent.R.drawable.img
                                    ),
                                    ImageBlurHashModel(
                                        blurHash = "@#$%TYU",
                                        data = com.orlandev.icontent.R.drawable.img
                                    ),
                                    ImageBlurHashModel(
                                        blurHash = "@#$%TYU",
                                        data = com.orlandev.icontent.R.drawable.img
                                    ),
                                ),

                                notImageFound = R.drawable.no_image,
                                onPageClicked = {

                                    clicked = it.toString()
                                    Log.d("Clicked", "Page clicked : $it")

                                }) {

                                Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                                    GradientEffect(
                                        backgroundColor = Color.Black,
                                        align = GradientAlignment.Center
                                    )
                                    CardTextInfo(
                                        title = "Orlando", subtitle = "Novas Rodriguez $clicked"
                                    )
                                }

                            }
                        }

                        item {
                            CardImageSlider(modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                                imageList = listOf(
                                    ImageBlurHashModel(
                                        blurHash = "@#$%TYU",
                                        data = com.orlandev.icontent.R.drawable.img
                                    )
                                ),
                                notImageFound = R.drawable.no_image,
                                onPageClicked = {
                                    clicked = it.toString()
                                    Log.d("Clicked", "Page clicked : $it")

                                }) {

                                Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                                    GradientEffect(
                                        backgroundColor = Color.Black,
                                        align = GradientAlignment.Center
                                    )
                                    CardTextInfo(
                                        title = "Orlando", subtitle = "Novas Rodriguez $clicked"
                                    )
                                }

                            }
                        }


                        item {
                            MarqueeText(
                                text = "This is a marquee text kfjwef klw flwkfj lkwef klwef lkwjef lkwfej werw erwekrj werlkjwe rlkwejr wjr wejrwjrwljrw ljr wlkejr w",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp),
                                overflow = TextOverflow.Ellipsis,
                                gradientEdgeColor = androidx.compose.material3.MaterialTheme.colorScheme.background.copy(
                                    alpha = 0.9f
                                )
                            )

                        }

                        item {
                            Card(modifier = Modifier) {
                                RatingReviewsBar(
                                    siteReview = "Califica este sitio",
                                    shareOption = "Comparte tu opinion con otros usuarios",
                                    comment = "Deja tu comentario...",
                                    buttonText = "Publicar",
                                    titleCardTextStyle = androidx.compose.material3.MaterialTheme.typography.titleSmall,
                                    subtitleCardTextStyle = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                                    onCommentTextFieldFocusChanged = {},
                                    onUserReview = { stars, comment ->
                                        Log.d("Review", "$stars - $comment")

                                    },
                                    activeColor = Color.Red
                                )
                            }
                        }

                        item {

                            val cmod = Modifier
                                .width(200.dp)
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color.White)

                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            ) {
                                item {

                                    Box(
                                        modifier = cmod
                                    ) {
                                        GradientEffect(
                                            backgroundColor = Color.Black,
                                            align = GradientAlignment.Start
                                        )
                                    }

                                }


                                item {

                                    Box(
                                        modifier = cmod
                                    ) {
                                        GradientEffect(
                                            backgroundColor = Color.Black,
                                            alphaValue = 0.7f,
                                            align = GradientAlignment.Center
                                        )
                                    }

                                }

                                item {

                                    Box(
                                        modifier = cmod
                                    ) {
                                        GradientEffect(
                                            backgroundColor = Color.Black,
                                            align = GradientAlignment.End
                                        )
                                    }

                                }

                                //item using a box container of a gradient effect vertically
                                item {
                                    Box(
                                        modifier = cmod
                                    ) {
                                        GradientEffect(
                                            backgroundColor = Color.Black,
                                            orientation = GradientEffectOrientation.Horizontally,
                                            align = GradientAlignment.Center
                                        )
                                    }
                                }

                                //item using a box container of a gradient effect orientation horizontally
                                item {
                                    Box(
                                        modifier = cmod
                                    ) {
                                        GradientEffect(
                                            backgroundColor = Color.Black,
                                            orientation = GradientEffectOrientation.Horizontally,
                                            align = GradientAlignment.Start
                                        )
                                    }
                                }


                            }
                        }


                        item {
                            ActionButtonsBar(
                                strokeColor = Color.Red, urlEnable = false
                            ) { btnEvents ->
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
                                    else -> {}
                                }
                            }
                        }

                        item {
                            Text(text = "Gallery")
                        }

                        item {
                            ContentUI(
                                contentUIModel = contentText, modifier = Modifier.fillMaxWidth()
                            )
                        }


                        item {
                            ContentUI(
                                contentUIModel = contentHtmlTextExpandable,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        item {
                            ContentUI(
                                contentUIModel = contentHtmlTextExpandable,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        item {
                            ContentUI(
                                contentUIModel = contentHtmlText, modifier = Modifier.fillMaxWidth()
                            )
                        }

                        item {
                            ContentUI(
                                contentUIModel = contentTextExpandable,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        item {
                            IGalleryStaggered(imageContentList = listImages)
                        }

                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            ) {
                                ContentUI(
                                    contentUIModel = carouselContent,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            ) {
                                ContentUI(
                                    contentUIModel = carouselContent,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }

                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            ) {
                                CarouselContainer(carouselDataList = carouselWithText,
                                    addGradient = true,
                                    onCarouselItemClick = {})
                            }
                        }
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            ) {
                                CarouselContainer(carouselDataList = carouselWithText,
                                    addGradient = true,
                                    onCarouselItemClick = {})
                            }
                        }

                        item {

                            ContentUI(
                                contentUIModel = contentPano,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                        }
                        item {

                            ContentUI(
                                contentUIModel = contentImage,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                        }
                        item {
                            ContentUI(
                                contentUIModel = contentVideo,
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


