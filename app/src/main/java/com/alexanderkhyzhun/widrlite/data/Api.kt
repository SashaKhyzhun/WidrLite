package com.alexanderkhyzhun.widrlite.data

import com.alexanderkhyzhun.widrlite.data.models.response.RPNewsItem
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
interface Api {

    @GET("/test")
    fun test(): Single<ResponseBody>

    @GET("/fetch/news/...")
    fun fetchNews(offsetStart: Int, offsetEnd: Int): Observable<RPNewsItem>

}