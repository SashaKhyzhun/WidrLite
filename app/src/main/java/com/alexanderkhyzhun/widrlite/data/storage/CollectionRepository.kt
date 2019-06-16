package com.alexanderkhyzhun.widrlite.data.storage

import com.alexanderkhyzhun.widrlite.data.models.ChatItem
import com.alexanderkhyzhun.widrlite.data.models.NewsItem
import com.alexanderkhyzhun.widrlite.ui.adapters.models.Message
import io.reactivex.Observable

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
interface CollectionRepository {

    fun fetchMessages(): Observable<List<Message>>
    fun fetchChatDetails(): Observable<ChatItem>

    fun updateChatDetails(item: ChatItem)
    fun updateMessages(data: List<Message>)


    fun fetchNewsFeed(): Observable<List<NewsItem>>

}