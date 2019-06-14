package com.alexanderkhyzhun.widrlite.domain.impl

import com.alexanderkhyzhun.widrlite.data.Api
import com.alexanderkhyzhun.widrlite.data.models.MessageItem
import com.alexanderkhyzhun.widrlite.data.toMessageItem
import com.alexanderkhyzhun.widrlite.domain.MessagesUseCase
import com.alexanderkhyzhun.widrlite.utils.generateRPMessages
import io.reactivex.Observable

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class MessagesUseCaseImpl(
    private val api: Api
) : MessagesUseCase {

    override fun fetchMessages(): Observable<List<MessageItem>> {
        return Observable
            .fromCallable { generateRPMessages() }
            .map { messages -> messages.map { it.toMessageItem() } }
    }


}
