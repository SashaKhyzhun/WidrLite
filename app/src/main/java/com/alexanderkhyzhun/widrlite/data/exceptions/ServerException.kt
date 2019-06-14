package com.alexanderkhyzhun.widrlite.data.exceptions

open class ServerException(
    val httpCode: Int,
    val errorMessage: String?
) : Throwable(errorMessage)