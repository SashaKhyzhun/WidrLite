package com.alexanderkhyzhun.widrlite.data.storage

import com.alexanderkhyzhun.widrlite.data.models.MessageItem
import com.alexanderkhyzhun.widrlite.data.models.ChatItem
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

}