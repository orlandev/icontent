package com.orlandev.icontent.components

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.orlandev.icontent.models.ContentUIModel
import com.orlandev.icontent.models.ContentUIType
import com.orlandev.icontent.utils.FIELD_IMAGE_BLUR_DELIMITIER
import com.smarttoolfactory.image.beforeafter.BeforeAfterImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ImageBeforeAfter(
    modifier: Modifier,
    contentUIModel: ContentUIModel,
) {
    if (contentUIModel.typeI == ContentUIType.BeforeAfter) {
        //field is URL[!]URL
        val imgRef = contentUIModel.field.split(FIELD_IMAGE_BLUR_DELIMITIER)

        if (imgRef.size == 2) {
            ImageBeforeAfter(modifier = modifier, before = imgRef[0], after = imgRef[1], loader = {
                CircularProgressIndicator()
            })
        }
    } else {
        Log.e("ImageBeforeAfter", "WRONG CONTENT TYPE: ${contentUIModel.typeI} ")
    }
}


@ExperimentalLayoutApi
@Composable
fun ImageBeforeAfter(
    modifier: Modifier = Modifier, before: String, after: String, loader: @Composable () -> Unit
) {


    val context = LocalContext.current

    val imageLoader = ImageLoader(context)

    val request = ImageRequest.Builder(context).data(before).build()
    val request2 = ImageRequest.Builder(context).data(after).build()

    val imagePainter = rememberAsyncImagePainter(
        model = request, imageLoader = imageLoader
    )

    val imagePainter2 = rememberAsyncImagePainter(
        model = request, imageLoader = imageLoader
    )

    var beforeBitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }

    var afterBitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }


    LaunchedEffect(key1 = imagePainter) {

        Log.d("ImageBeforeAfter", "CALL LAUNCHED EFFECT")

        if (beforeBitmap == null) {
            launch {

                val result = (imageLoader.execute(request) as SuccessResult).drawable

                /*val vibrant = Palette.from(bitmap)
                    .generate()
                    .getVibrantolor(defaultColor)
                        */

                beforeBitmap = (result as BitmapDrawable).bitmap
            }
        }
    }

    LaunchedEffect(key1 = imagePainter2) {

        Log.d("ImageBeforeAfter", "CALL LAUNCHED EFFECT")

        if (afterBitmap == null) {
            launch {

                val result = (imageLoader.execute(request2) as SuccessResult).drawable

                /*val vibrant = Palette.from(bitmap)
                    .generate()
                    .getVibrantolor(defaultColor)
                        */

                afterBitmap = (result as BitmapDrawable).bitmap
            }
        }
    }

    val contentScale by remember { mutableStateOf(ContentScale.FillBounds) }

    if (afterBitmap != null && beforeBitmap != null) {
        BeforeAfterImage(
            modifier = modifier,
            beforeImage = beforeBitmap!!.asImageBitmap(),
            afterImage = afterBitmap!!.asImageBitmap(),
            afterLabel = {},
            beforeLabel = {},
            contentScale = contentScale
        )
    } else {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            loader()
        }
    }
}