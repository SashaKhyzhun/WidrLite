package com.alexanderkhyzhun.widrlite.domain

import com.alexanderkhyzhun.widrlite.data.models.MessageItem
import com.alexanderkhyzhun.widrlite.data.models.ChatItem
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
interface ChatUseCase {

    fun fetchChatData(): Observable<ChatItem>
    fun updateChatData(item: ChatItem)

    fun fetchMessages(): Observable<List<MessageItem>>
    fun updateMessages(data: List<MessageItem>)

    fun messageText(): BehaviorSubject<CharSequence>

}