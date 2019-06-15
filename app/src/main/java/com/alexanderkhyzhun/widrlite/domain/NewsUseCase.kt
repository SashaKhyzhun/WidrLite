package com.alexanderkhyzhun.widrlite.domain

import com.alexanderkhyzhun.widrlite.data.models.NewsItem
import io.reactivex.Observable

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
interface NewsUseCase {

    fun fetchNewsFeed(): Observable<List<NewsItem>>

}