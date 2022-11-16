package com.orlandev.icontent.components

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import com.ondev.blurhashkt.BlurhashDecoder
import com.orlandev.icontent.models.ContentModel
import com.orlandev.icontent.utils.FIELD_IMAGE_BLUR_DELIMITIER
import com.orlandev.icontent.utils.getBitmapFromURL
import kotlinx.coroutines.launch


/***
 * Google VR PanoView needs the activity as context..,.
 * for this we pass said context in the contentModel by passing
 * the context as a parameter we can put this component inside a Fragment
 */
@Composable
fun IPanoView(contentModel: ContentModel, modifier: Modifier=Modifier) {

    val imgRef = contentModel.field.split(FIELD_IMAGE_BLUR_DELIMITIER)

    val panoBitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val imageUrl = imgRef[0] //Image URL
    val blurhash = imgRef[1] //Blurhash

    val context = LocalContext.current as Activity

    val imageLoader = ImageLoader(context)

    val request = ImageRequest.Builder(context).data(imageUrl).build()

    val imagePainter = rememberImagePainter(
        request = request, imageLoader = imageLoader
    )

    LaunchedEffect(Unit) {

        try {

            val result = (imageLoader.execute(request) as SuccessResult).drawable

            /*val vibrant = Palette.from(bitmap)
                .generate()
                .getVibrantolor(defaultColor)
                    */

            panoBitmap.value = (result as BitmapDrawable).bitmap

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    //val blurhash = imgRef[1] //Blurhash
    //val blurhash = "LEHV6nWB2yk8pyo0adR*.7kCMdnj"
    val bitmapPlaceholder = BlurhashDecoder.decode(blurhash, 100, 50)

    if (panoBitmap.value == null) {
        Image(
            painter = rememberImagePainter(bitmapPlaceholder),
            modifier = modifier,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    } else {
        AndroidView(
            {
                VrPanoramaView(context).apply {
                    val options = VrPanoramaView.Options()
                    options.inputType = VrPanoramaView.Options.TYPE_MONO

                    setInfoButtonEnabled(false)
                    setFullscreenButtonEnabled(true)
                    setStereoModeButtonEnabled(true)
                    setTouchTrackingEnabled(true)

                    loadImageFromBitmap(panoBitmap.value, options)
                }
            }, modifier = modifier
        ) // Occupy the max size in the Compose UI tree
    }
}


@Composable
fun PanoView(imageResUrl: String, modifier: Modifier = Modifier, @DrawableRes localImage: Int) {

    val context = LocalContext.current as Activity

    var bitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }

    var isLoading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {
        launch {
            bitmap = getBitmapFromURL(imageResUrl)
            isLoading = false
        }
    }

    if (isLoading) {
        //TODO MAKE LOADING EFFECT
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    if (bitmap == null) {
        Image(
            modifier = modifier,
            painter = painterResource(id = localImage),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        return
    }

    AndroidView(
        {
            VrPanoramaView(context).apply {
                val options = VrPanoramaView.Options()
                options.inputType = VrPanoramaView.Options.TYPE_MONO

                setInfoButtonEnabled(false)
                setFullscreenButtonEnabled(false)
                setStereoModeButtonEnabled(false)
                setTouchTrackingEnabled(false)

                loadImageFromBitmap(bitmap, options)
            }
        }, modifier = modifier
    ) // Occupy the max size in the Compose UI tree
}

@Composable
fun PanoView(@DrawableRes imageRes: Int, modifier: Modifier = Modifier) {

    val context = LocalContext.current as Activity

    val bitmap: State<Bitmap> = remember {
        derivedStateOf {
            BitmapFactory.decodeResource(context.resources, imageRes)
        }
    }

    AndroidView(
        {
            VrPanoramaView(context).apply {
                val options = VrPanoramaView.Options()
                options.inputType = VrPanoramaView.Options.TYPE_MONO

                setInfoButtonEnabled(false)
                setFullscreenButtonEnabled(false)
                setStereoModeButtonEnabled(false)
                setTouchTrackingEnabled(false)

                loadImageFromBitmap(bitmap.value, options)
            }
        }, modifier = modifier
    ) // Occupy the max size in the Compose UI tree

}
