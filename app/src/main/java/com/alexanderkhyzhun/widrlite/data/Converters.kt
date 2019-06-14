package com.alexanderkhyzhun.widrlite.data

import com.alexanderkhyzhun.widrlite.data.models.NotificationItem
import com.alexanderkhyzhun.widrlite.data.models.response.RPNotification

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
fun RPNotification.toNotificationItem(): NotificationItem
        = NotificationItem(id, image, name, message, `when`, isNew)