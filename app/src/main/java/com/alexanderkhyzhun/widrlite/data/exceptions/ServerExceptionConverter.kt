package com.alexanderkhyzhun.widrlite.data.exceptions

import com.google.gson.Gson

class ServerExceptionConverter(private val gson: Gson) {

    fun fromNetwork(code: Int, message: String?): ServerException {
        var errorMessage: GeneralServiceException? = null

        try {
            errorMessage = gson.fromJson(message, GeneralServiceException::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ServerException(code, errorMessage?.processError())
    }

    fun fromNetworkApiError(code: Int, message: String?): ServerException {
        return ServerException(code, "$code $message")
    }

}