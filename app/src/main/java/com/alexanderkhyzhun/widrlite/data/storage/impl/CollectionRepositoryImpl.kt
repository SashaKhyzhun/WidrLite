package com.alexanderkhyzhun.widrlite.data.storage.impl

import com.alexanderkhyzhun.widrlite.data.Api
import com.alexanderkhyzhun.widrlite.data.models.ChatItem
import com.alexanderkhyzhun.widrlite.data.models.NewsItem
import com.alexanderkhyzhun.widrlite.data.storage.CollectionRepository
import com.alexanderkhyzhun.widrlite.data.toNewsItem
import com.alexanderkhyzhun.widrlite.ui.adapters.models.Message
import com.alexanderkhyzhun.widrlite.utils.generateRPNews
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class CollectionRepositoryImpl(
    private val api: Api
) : CollectionRepository {

    private val conversationSubj = BehaviorSubject.create<ChatItem>()
    private val messagesSubj = BehaviorSubject.create<List<Message>>()

    override fun fetchMessages(): Observable<List<Message>> = messagesSubj

    override fun fetchChatDetails(): Observable<ChatItem> = conversationSubj

    override fun updateChatDetails(item: ChatItem) {
        conversationSubj.onNext(item)
    }

    override fun updateMessages(data: List<Message>) {
        messagesSubj.onNext(data)
    }

    override fun fetchNewsFeed(): Observable<List<NewsItem>> {
        //api.fetchNews()
        return Observable
            .fromCallable { generateRPNews() }
            .map { data ->
                data.map {
                    it.toNewsItem()
                }
            }
    }

}

