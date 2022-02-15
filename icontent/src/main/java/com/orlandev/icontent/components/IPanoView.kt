package com.orlandev.icontent.components

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ComponentActivity
import coil.ImageLoader
import coil.request.ImageRequest
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import com.ondev.blurhashkt.BlurhashDecoder
import com.orlandev.icontent.utils.FIELD_IMAGE_BLUR_DELIMITIER
import kotlinx.coroutines.launch

@Composable
fun IPanoView(field: String, modifier: Modifier) {
    val imgRef = field.split(FIELD_IMAGE_BLUR_DELIMITIER)
    val ctx = LocalContext.current as ComponentActivity
    val panoBitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }
    val coroutineScope = rememberCoroutineScope()

    if (true) {
    //if (imgRef.size == 2) {
        //val blurhash = imgRef[1] //Blurhash
        val blurhash = "LEHV6nWB2yk8pyo0adR*.7kCMdnj"
        val bitmapPlaceholder = BlurhashDecoder.decode(blurhash, 4, 3)

        LaunchedEffect(Unit) {
            coroutineScope.launch {
                try {
                    val loader = ImageLoader(ctx)
                    val request = ImageRequest.Builder(ctx)
                        .data("https://ik.imagekit.io/lgqp0wffgtp/place_pano_UFHCG0Inz.webp")
                        .allowHardware(false) // Disable hardware bitmaps.
                        .build()
                    val result = (loader.execute(request) as BitmapDrawable).bitmap
                    panoBitmap.value = result
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        if (panoBitmap.value == null) {
            Image(
                bitmap = bitmapPlaceholder as ImageBitmap,
                modifier = modifier,
               // contentScale = ContentScale.Crop,
                contentDescription = null
            )
        } else {
            AndroidView(
                {
                    VrPanoramaView(ctx).apply {
                        val options = VrPanoramaView.Options()
                        options.inputType = VrPanoramaView.Options.TYPE_MONO

                        setInfoButtonEnabled(false)
                        setFullscreenButtonEnabled(true)
                        setStereoModeButtonEnabled(true)
                        setTouchTrackingEnabled(true)

                        loadImageFromBitmap(panoBitmap.value, options)
                    }
                },
                modifier = modifier.placeholder(
                    panoBitmap.value == null,
                    highlight = PlaceholderHighlight.shimmer(
                        highlightColor = Color.White,
                    ), color = Color.Gray
                ) // Occupy the max size in the Compose UI tree
            )
        }


    }


}