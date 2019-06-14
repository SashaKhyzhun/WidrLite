package com.alexanderkhyzhun.widrlite.data.storage

import com.alexanderkhyzhun.widrlite.data.models.MessageItem
import com.alexanderkhyzhun.widrlite.data.models.UserItem
import io.reactivex.Observable

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
interface CollectionRepository {

    fun messages(): Observable<List<MessageItem>>
    fun conversation(item: UserItem)

}