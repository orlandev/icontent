package com.orlandev.icontent.components

import android.app.Activity
import android.graphics.Bitmap
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import com.ondev.blurhashkt.BlurhashDecoder
import com.ondev.imageblurkt_lib.ImageBlurHashModel
import com.orlandev.icontent.utils.fetchImageAsBitmap

//TODO EXTRACT THIS COMPONENT TO A SEPARATED MODULE

/***
 * Google VR PanoView needs the activity as context..,.
 * for this we pass said context in the contentModel by passing
 * the context as a parameter we can put this component inside a Fragment
 */

@Composable
fun PanoView(
    model: ImageBlurHashModel,
    modifier: Modifier = Modifier,
    setInfoButtonEnabled: Boolean = false,
    setFullscreenButtonEnabled: Boolean = true,
    setStereoModeButtonEnabled: Boolean = true,
    setTouchTrackingEnabled: Boolean = true
) {

    val panoBitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val context = LocalContext.current as Activity

    LaunchedEffect(model) {
        panoBitmap.value = fetchImageAsBitmap(context, model.data)
    }

    val bitmapPlaceholder = BlurhashDecoder.decode(model.blurHash, 4, 3)

    Crossfade(targetState = panoBitmap.value, animationSpec = tween(800)) {
        if (it == null) {
            AsyncImage(
                model = bitmapPlaceholder,
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

                        setInfoButtonEnabled(setInfoButtonEnabled)
                        setFullscreenButtonEnabled(setFullscreenButtonEnabled)
                        setStereoModeButtonEnabled(setStereoModeButtonEnabled)
                        setTouchTrackingEnabled(setTouchTrackingEnabled)

                        loadImageFromBitmap(panoBitmap.value, options)
                    }
                }, modifier = modifier
            ) // Occupy the max size in the Compose UI tree
        }
    }
}