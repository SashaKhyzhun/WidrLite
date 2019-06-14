package com.alexanderkhyzhun.widrlite.domain.impl

import com.alexanderkhyzhun.widrlite.data.models.MessageItem
import com.alexanderkhyzhun.widrlite.data.storage.CollectionRepository
import com.alexanderkhyzhun.widrlite.domain.ChatUseCase
import io.reactivex.Observable

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class ChatUseCaseImpl(
    private val collectionRepo: CollectionRepository
) : ChatUseCase {

    override fun fetchMessages(): Observable<List<MessageItem>> {
        return collectionRepo.messages()
    }

}