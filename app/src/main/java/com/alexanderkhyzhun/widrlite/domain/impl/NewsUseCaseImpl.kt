package com.alexanderkhyzhun.widrlite.domain.impl

import com.alexanderkhyzhun.widrlite.data.models.NewsItem
import com.alexanderkhyzhun.widrlite.data.models.response.RPNewsItem
import com.alexanderkhyzhun.widrlite.data.storage.CollectionRepository
import com.alexanderkhyzhun.widrlite.domain.NewsUseCase
import io.reactivex.Observable

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class NewsUseCaseImpl(
    private val collectionRepo: CollectionRepository
) : NewsUseCase {

    override fun fetchNewsFeed(): Observable<List<NewsItem>> {
        return collectionRepo.fetchNewsFeed()
    }
}