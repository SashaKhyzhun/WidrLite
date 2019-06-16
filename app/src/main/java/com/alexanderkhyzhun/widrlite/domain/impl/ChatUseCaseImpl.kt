package com.alexanderkhyzhun.widrlite.domain.impl

import com.alexanderkhyzhun.widrlite.data.models.MessageItem
import com.alexanderkhyzhun.widrlite.data.models.ChatItem
import com.alexanderkhyzhun.widrlite.data.storage.CollectionRepository
import com.alexanderkhyzhun.widrlite.domain.ChatUseCase
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class ChatUseCaseImpl(
    private val collectionRepo: CollectionRepository
) : ChatUseCase {

    private val messageChangesSubject = BehaviorSubject.create<CharSequence>()

    override fun fetchChatData(): Observable<ChatItem> {
        return collectionRepo.fetchChatDetails()
    }

    override fun updateChatData(item: ChatItem) {
        collectionRepo.updateChatDetails(item)
    }


    override fun fetchMessages(): Observable<List<MessageItem>> {
        return collectionRepo.fetchMessages()
    }

    override fun updateMessages(data: List<MessageItem>) {
        collectionRepo.updateMessages(data)
    }

    override fun messageText(): BehaviorSubject<CharSequence> = messageChangesSubject
}