package com.orlandev.ishowcontent

import android.text.Html
import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HtmlTextTest {


    @Test
    fun testHtmlText(){
        val textX= Html.fromHtml("<b>test</b>")
        Assert.assertEquals("test",textX)
    }

}