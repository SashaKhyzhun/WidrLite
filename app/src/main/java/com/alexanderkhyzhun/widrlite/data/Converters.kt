package com.alexanderkhyzhun.widrlite.data

import com.alexanderkhyzhun.widrlite.data.models.MessageItem
import com.alexanderkhyzhun.widrlite.data.models.NotificationItem
import com.alexanderkhyzhun.widrlite.data.models.response.RPMessage
import com.alexanderkhyzhun.widrlite.data.models.response.RPNotification

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
fun RPNotification.toNotificationItem(): NotificationItem
        = NotificationItem(id, image, name, message, `when`, isNew)


fun RPMessage.toMessageItem(): MessageItem = MessageItem(
    senderId, senderPhoto, senderName, senderLocation, senderJob, body, date, amount, isNew
)