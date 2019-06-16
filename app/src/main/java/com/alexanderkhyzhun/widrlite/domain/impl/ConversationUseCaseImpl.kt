package com.alexanderkhyzhun.widrlite.domain.impl

import com.alexanderkhyzhun.widrlite.data.Api
import com.alexanderkhyzhun.widrlite.data.models.ConversationItem
import com.alexanderkhyzhun.widrlite.data.models.ChatItem
import com.alexanderkhyzhun.widrlite.data.storage.CollectionRepository
import com.alexanderkhyzhun.widrlite.data.toConversationItem
import com.alexanderkhyzhun.widrlite.domain.ConversationUseCase
import com.alexanderkhyzhun.widrlite.utils.generateRPConversations
import io.reactivex.Observable

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class ConversationUseCaseImpl(
    private val api: Api,
    private val collectionRepo: CollectionRepository
) : ConversationUseCase {

    override fun fetchConversations(): Observable<List<ConversationItem>> {
        return Observable
            .fromCallable { generateRPConversations() }
            .map { messages ->
                messages.map {
                    it.toConversationItem()
                }
            }
    }

    override fun updateSelectedChat(chatItem: ChatItem) {
        collectionRepo.updateChatDetails(chatItem)
    }
}
