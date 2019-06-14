package com.alexanderkhyzhun.widrlite.data.exceptions

import com.google.gson.annotations.SerializedName

class GeneralServiceException(
    @SerializedName("message")
    val message: String?
) {

    fun processError() = message?.let { message } ?: ""


}