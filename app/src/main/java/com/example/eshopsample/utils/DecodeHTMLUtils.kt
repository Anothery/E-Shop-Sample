package com.example.eshopsample.utils

class DecodeHTMLUtils {
    companion object {
        fun decodeString(text: String): String {
            return text.replace("&amp;", "&")
        }
    }
}