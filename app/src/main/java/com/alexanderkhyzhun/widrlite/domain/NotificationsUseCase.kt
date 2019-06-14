package com.alexanderkhyzhun.widrlite.domain

import com.alexanderkhyzhun.widrlite.data.models.NotificationItem
import com.alexanderkhyzhun.widrlite.data.models.response.RPNotification
import io.reactivex.Observable
import java.util.*

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
interface NotificationsUseCase {

    fun fetchNotification(): Observable<List<NotificationItem>>
}