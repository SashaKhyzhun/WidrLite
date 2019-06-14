package com.alexanderkhyzhun.widrlite.data.storage.impl

import com.alexanderkhyzhun.widrlite.data.models.MessageItem
import com.alexanderkhyzhun.widrlite.data.models.ChatItem
import com.alexanderkhyzhun.widrlite.data.storage.CollectionRepository
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class CollectionRepositoryImpl : CollectionRepository {

    private val conversationSubj = BehaviorSubject.create<ChatItem>()
    private val messagesSubj = BehaviorSubject.create<List<MessageItem>>()

    override fun fetchMessages(): Observable<List<MessageItem>> = messagesSubj

    override fun fetchChatDetails(): Observable<ChatItem> = conversationSubj

    override fun updateChatDetails(item: ChatItem) {
        conversationSubj.onNext(item)
    }

    override fun updateMessages(data: List<MessageItem>) {
        messagesSubj.onNext(data)
    }
}