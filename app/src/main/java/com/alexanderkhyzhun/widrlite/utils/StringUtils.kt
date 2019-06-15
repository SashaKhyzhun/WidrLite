package com.alexanderkhyzhun.widrlite.utils

import android.util.Patterns

/**
 * @author SashaKhyzhun
 * Created on 5/5/19.
 */



/**
 * Clear the text with it
 */
fun empty() = ""

/**
 * Check if this string is valid web url
 *
 * @see Patterns.WEB_URL
 */
fun String.isValidWebUrl() = Patterns.WEB_URL.matcher(this).matches()

/**
 * Check if this string is valid web url
 *
 * @see Patterns.EMAIL_ADDRESS
 */
fun String.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

/**
 * Check if this string is valid web url
 *
 * @see Patterns.PHONE
 */
fun String.isValidPhoneNumber() = Patterns.PHONE.matcher(this).matches()


fun String.threeDots(n: Int): String {
    return if (this.length >= n) "${this.substring(0, n)}..." else this
}

fun getEmojiByUnicode(unicode: Int): String = String(Character.toChars(unicode))