package com.alexanderkhyzhun.widrlite.domain.impl

import android.annotation.SuppressLint
import com.alexanderkhyzhun.widrlite.data.Api
import com.alexanderkhyzhun.widrlite.data.models.NotificationItem
import com.alexanderkhyzhun.widrlite.data.toNotificationItem
import com.alexanderkhyzhun.widrlite.domain.NotificationsUseCase
import com.alexanderkhyzhun.widrlite.utils.generateRPNotifications
import io.reactivex.Observable

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
@SuppressLint("CheckResult")
class NotificationsUseCaseImpl(
    private val api: Api
) : NotificationsUseCase {


    override fun fetchNotification(): Observable<List<NotificationItem>> {
        //api.test()
        return Observable
            .fromCallable { generateRPNotifications() }
            .map { notifications ->
                notifications.map { it.toNotificationItem() }
            }

    }

}