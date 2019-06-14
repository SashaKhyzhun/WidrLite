package com.alexanderkhyzhun.widrlite.data.storage.impl

import com.alexanderkhyzhun.widrlite.data.models.MessageItem
import com.alexanderkhyzhun.widrlite.data.models.UserItem
import com.alexanderkhyzhun.widrlite.data.storage.CollectionRepository
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class CollectionRepositoryImpl : CollectionRepository {

    private val conversation = BehaviorSubject.create<UserItem>()
    private val messagesSubject = BehaviorSubject.create<List<MessageItem>>()

    override fun messages(): Observable<List<MessageItem>> = messagesSubject

    override fun conversation(item: UserItem) {
        conversation.onNext(item)
    }
}