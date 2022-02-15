package com.orlandev.icontent.components

import android.content.Context
import android.net.Uri
import android.util.Log
import android.util.SparseArray
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.PlayerView.SHOW_BUFFERING_ALWAYS
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource

@ExperimentalAnimationApi
@Composable
fun IVideoPlayer(
    modifier: Modifier = Modifier,
    url: String,
    loadFromYoutube: Boolean = true,
    playWhenReady: Boolean = true,
    useControllerB: Boolean = true,
    useArtworks: Boolean = true
) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            if (!loadFromYoutube) {
                val firstVideoUri = Uri.parse(url)
                val firstItem = MediaItem.fromUri(firstVideoUri)
                this.setMediaItem(firstItem)
                this.prepare()
            } else {
                playYoutubeUrl(context = context, this, url)
            }
            this.repeatMode = Player.REPEAT_MODE_ALL
        }
    }

    exoPlayer.playWhenReady = playWhenReady

    LocalLifecycleOwner.current.lifecycle.addObserver(object : LifecycleEventObserver {

        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            when (event) {
                Lifecycle.Event.ON_STOP -> {
                    exoPlayer.pause()
                }
                Lifecycle.Event.ON_START -> {
                    if (exoPlayer.isPlaying.not()) {
                        exoPlayer.play()
                    }
                }
                else -> {
                    exoPlayer.pause()
                }
            }
        }
    })

    DisposableEffect(
        AndroidView(
            modifier = modifier,
            factory = {
                PlayerView(context).apply {
                    useController = useControllerB
                    useArtwork = useArtworks
                    keepScreenOn = true
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    setShowBuffering(SHOW_BUFFERING_ALWAYS)
                    player = exoPlayer
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            })
    ) {
        onDispose {
            exoPlayer.release()
        }
    }

}

/*
  *
  * 171 webm audio only DASH audio 115k , audio@128k (44100Hz), 2.59MiB (worst)
  140 m4a audio only DASH audio 129k , audio@128k (44100Hz), 3.02MiB
  141 m4a audio only DASH audio 255k , audio@256k (44100Hz), 5.99MiB
  160 mp4 256x144 DASH video 111k , 12fps, video only, 2.56MiB
  247 webm 1280x720 DASH video 1807k , 1fps, video only, 23.48MiB
  136 mp4 1280x720 DASH video 2236k , 24fps, video only, 27.73MiB
  248 webm 1920x1080 DASH video 3993k , 1fps, video only, 42.04MiB
  137 mp4 1920x1080 DASH video 4141k , 24fps, video only, 60.28MiB
  43 webm 640x360
  18 mp4 640x360
  22 mp4 1280x720 (best)
  * */
private fun playYoutubeUrl(context: Context, simpleExoPlayer: ExoPlayer, videoPromo: String) {
    object : YouTubeExtractor(context) {
        override fun onExtractionComplete(
            ytFiles: SparseArray<YtFile>?,
            videoMeta: VideoMeta?,
        ) {
            if (ytFiles != null) {
                val videoTag = 18 //Tag 1080
                val audioTag = 140 //Tag 1080

                Log.d("VIDEO-SOURCE", "VIDEO: ${ytFiles.get(videoTag).url}")

                val audioSource: MediaSource =
                    ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
                        .createMediaSource(MediaItem.fromUri(ytFiles.get(audioTag).url))
                val videoSource: MediaSource =
                    ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
                        .createMediaSource(MediaItem.fromUri(ytFiles.get(videoTag).url))

                /*(simpleExoPlayer as SphericalGLSurfaceView?)!!.setDefaultStereoMode(
                    C.STEREO_MODE_STEREO_MESH
                )*/
                simpleExoPlayer.setMediaSource(
                    MergingMediaSource(
                        true,
                        videoSource,
                        audioSource
                    ),
                    true
                )
                simpleExoPlayer.prepare()
            }
        }
    }.extract(videoPromo)
}