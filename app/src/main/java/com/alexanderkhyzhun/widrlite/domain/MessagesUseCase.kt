package com.alexanderkhyzhun.widrlite.domain

import com.alexanderkhyzhun.widrlite.data.models.MessageItem
import io.reactivex.Observable

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
interface MessagesUseCase {

    fun fetchMessages(): Observable<List<MessageItem>>

}