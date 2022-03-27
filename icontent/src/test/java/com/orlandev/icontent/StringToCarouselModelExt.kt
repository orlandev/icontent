package com.orlandev.icontent

import com.orlandev.icontent.utils.toCarouselModelList
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class StringToCarouselModelExt {
    @Test
    fun extension_isCorrect() {
        val dataString =
            "https://url-image/image.jpg[!]JF023KF023F9K3F[!]https://url-image/image.jpg[!]JF023KF023F9K3F[!]https://url-image/image.jpg[!]JF023KF023F9K3F[!]https://url-image/image.jpg[!]JF023KF023F9K3F[!]"
        val sizeOfListResult=dataString.toCarouselModelList().size
        assertEquals(4, sizeOfListResult)
    }
}