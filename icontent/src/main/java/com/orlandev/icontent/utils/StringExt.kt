package com.orlandev.icontent.utils

fun String.smartTruncate(max: Int = 120, suffix: String = "..."): String {
    return if (this.length < max) {
        this
    } else {
        "${this.substring(0, this.substring(0, max - suffix.length).lastIndexOf(' '))}${suffix}"
    }
}

