package com.alexanderkhyzhun.widrlite.data.impl

import com.alexanderkhyzhun.widrlite.data.exceptions.ServerExceptionConverter
import com.alexanderkhyzhun.widrlite.data.storage.StorageRepository
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import java.util.*


class InterceptorFactory(
    private val gson: Gson,
    private val storage: StorageRepository
) {

    val converter by lazy { ServerExceptionConverter(gson) }

    fun loggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    fun tokenInterceptor(): Interceptor {
        return Interceptor { chain ->
            val sessionId = chain.request().newBuilder()
            val response = chain.proceed(sessionId.build())
            response
        }
    }


    fun localeInterceptor(): Interceptor {
        return Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.header(LOCALE_KEY, Locale.getDefault().language)
            val response = chain.proceed(requestBuilder.build())
            response
        }
    }


    fun responseInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder().build()

            val response = try {
                chain.proceed(request)
            } catch (e: Exception) {
                throw e
            }

            val code = response.code()

            when (code) {

                in 400..451 -> throw converter.fromNetwork(code, response.body()?.string())

                in 500..511 -> throw converter.fromNetworkApiError(code, response.body()?.string())

            }

            return@Interceptor response
        }
    }

    companion object {
        const val REGISTRATION_TOKEN = "X-CSRF-Token"
        const val LOCALE_KEY = "Locale"
        const val AUTHORIZATION = "Authorization"
        const val CONTENT_TYPE_API_JSON = "Content-Type"

        const val SET_COOKIE_KEY = "Set-Cookie"
        const val COOKIE_KEY = "Cookie"

        const val USERNAME = "api"
        const val PASSWORD = "m02Kx3IKdnd4"

        const val ACCEPT = "Accept"
        const val CONTENT_TYPE = "Content-Type"


        const val X_CSRF_Token = "qwerty_qwerty_qwerty"

        const val BEARER = "Bearer qwerty_qwerty_qwerty"
        const val URL_ENCODED = "application/x-www-form-urlencoded"
        const val VND_API_JSON = "application/vnd.api+json"

    }
}
