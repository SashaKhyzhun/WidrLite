package com.alexanderkhyzhun.widrlite.utils

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream
import android.graphics.BitmapFactory




/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */

fun bitmapToString(bitmap: Bitmap): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val b = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(b, Base64.DEFAULT)
}

/**
 * @param encodedString
 * @return bitmap (from given string)
 */
fun stringToBitmap(encodedString: String): Bitmap? {
    return try {
        val encodeByte = Base64.decode(encodedString, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
    } catch (e: Exception) {
        e.message
        null
    }

}