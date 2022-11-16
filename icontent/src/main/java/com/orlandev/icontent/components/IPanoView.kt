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
import com.orlandev.icontent.models.ContentModel
import com.orlandev.icontent.utils.FIELD_IMAGE_BLUR_DELIMITIER
import com.orlandev.icontent.utils.getCoilBitmap


/***
 * Google VR PanoView needs the activity as context..,.
 * for this we pass said context in the contentModel by passing
 * the context as a parameter we can put this component inside a Fragment
 */
@Composable
fun IPanoView(
    contentModel: ContentModel,
    modifier: Modifier = Modifier,
    setInfoButtonEnabled: Boolean = false,
    setFullscreenButtonEnabled: Boolean = true,
    setStereoModeButtonEnabled: Boolean = true,
    setTouchTrackingEnabled: Boolean = true
) {

    val imgRef = contentModel.field.split(FIELD_IMAGE_BLUR_DELIMITIER)

    val panoBitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val imageUrl = imgRef[0] //Image URL
    val blurhash = imgRef[1] //Blurhash

    val context = LocalContext.current as Activity

    LaunchedEffect(Unit) {
        panoBitmap.value = getCoilBitmap(context, imageUrl)
    }

    //val blurhash = imgRef[1] //Blurhash
    //val blurhash = "LEHV6nWB2yk8pyo0adR*.7kCMdnj"
    val bitmapPlaceholder = BlurhashDecoder.decode(blurhash, 100, 50)

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