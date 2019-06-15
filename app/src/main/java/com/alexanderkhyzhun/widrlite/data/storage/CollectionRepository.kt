package com.alexanderkhyzhun.widrlite.data.storage

import com.alexanderkhyzhun.widrlite.data.models.MessageItem
import com.alexanderkhyzhun.widrlite.data.models.ChatItem
import com.alexanderkhyzhun.widrlite.data.models.NewsItem
import com.alexanderkhyzhun.widrlite.data.models.response.RPNewsItem
import io.reactivex.Observable

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
interface CollectionRepository {

    fun fetchMessages(): Observable<List<MessageItem>>
    fun fetchChatDetails(): Observable<ChatItem>

    fun updateChatDetails(item: ChatItem)
    fun updateMessages(data: List<MessageItem>)


    fun fetchNewsFeed(): Observable<List<NewsItem>>

}