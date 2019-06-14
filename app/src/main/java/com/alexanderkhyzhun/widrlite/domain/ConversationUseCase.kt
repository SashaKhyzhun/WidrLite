package com.alexanderkhyzhun.widrlite.domain

import com.alexanderkhyzhun.widrlite.data.models.ConversationItem
import com.alexanderkhyzhun.widrlite.data.models.ChatItem
import io.reactivex.Observable

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
interface ConversationUseCase {

    fun fetchConversations(): Observable<List<ConversationItem>>

    fun updateSelectedChat(chatItem: ChatItem)

}