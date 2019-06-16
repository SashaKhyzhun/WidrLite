package com.alexanderkhyzhun.widrlite.domain

import com.alexanderkhyzhun.widrlite.data.models.ChatItem
import com.alexanderkhyzhun.widrlite.ui.adapters.models.Message
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
interface ChatUseCase {

    fun fetchChatData(): Observable<ChatItem>
    fun updateChatData(item: ChatItem)

    fun fetchMessages(): Observable<List<Message>>
    fun updateMessages(data: List<Message>)

    fun messageText(): BehaviorSubject<CharSequence>

}