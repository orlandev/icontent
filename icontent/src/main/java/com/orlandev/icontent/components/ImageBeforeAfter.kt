package com.orlandev.icontent.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.request.ImageRequest
import com.smarttoolfactory.image.beforeafter.BeforeAfterImage
import kotlinx.coroutines.launch

@ExperimentalComposeApi
@Composable
fun ImageBeforeAfter(
    before:String,
    after:String
) {

    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    val beforeBitmapState = rememberSaveable {
        mutableStateOf<ImageBitmap?>(null)
    }

    val afterBitmapState = rememberSaveable {
        mutableStateOf<ImageBitmap?>(null)
    }

    LaunchedEffect(Unit) {
            val beforeBitmap =
                getImageAsBitmap("https://images.dog.ceo/breeds/saluki/n02091831_3400.jpg", context)

            val afterBitmap =
                getImageAsBitmap("https://blurha.sh/ea9e499f8080ce9956a8.jpg", context)

            beforeBitmapState.value = beforeBitmap?.asImageBitmap()
            afterBitmapState.value = afterBitmap?.asImageBitmap()
    }


    val imageBefore = ImageBitmap.imageResource(
        LocalContext.current.resources, com.orlandev.icontent.R.drawable.img
    )

    val imageAfter = ImageBitmap.imageResource(
        LocalContext.current.resources, com.orlandev.icontent.R.drawable.img2
    )

    val contentScale by remember { mutableStateOf(ContentScale.FillBounds) }



    if (beforeBitmapState.value != null && afterBitmapState.value != null) {

        BeforeAfterImage(
            modifier = Modifier
                .shadow(1.dp, RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .aspectRatio(4 / 3f),
            beforeImage = beforeBitmapState.value!!,
            afterImage = afterBitmapState.value!!,
            contentScale = contentScale
        )

    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), contentAlignment = Alignment.Center
        ) {

            CircularProgressIndicator()

        }
    }

}

internal fun getImageAsBitmap(url: String, context: Context): Bitmap? {
    var bitmap: Bitmap? = null
    val loader = ImageLoader(context)
    val requestBuilder = ImageRequest.Builder(context)
    val beforeRequest = requestBuilder.data(url).allowHardware(false) // Disable hardware bitmaps.
        .target { result ->

            Log.d("ICOIL","RESULT FETCH IMAGE")

            bitmap = (result as BitmapDrawable).bitmap

        }.build()

    //val result = (loader.execute(beforeRequest) as SuccessResult).drawable

    val disposable = loader.enqueue(beforeRequest)

    return bitmap
}