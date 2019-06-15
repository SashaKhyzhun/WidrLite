package com.alexanderkhyzhun.widrlite.data

import com.alexanderkhyzhun.widrlite.data.models.ConversationItem
import com.alexanderkhyzhun.widrlite.data.models.NewsItem
import com.alexanderkhyzhun.widrlite.data.models.NotificationItem
import com.alexanderkhyzhun.widrlite.data.models.response.RPConversation
import com.alexanderkhyzhun.widrlite.data.models.response.RPNewsItem
import com.alexanderkhyzhun.widrlite.data.models.response.RPNotification

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
fun RPNotification.toNotificationItem(): NotificationItem
        = NotificationItem(id, image, name, message, `when`, isNew)


fun RPConversation.toConversationItem(): ConversationItem = ConversationItem(
    senderId, senderPhoto, senderName, senderLocation, senderJob, body, date, amount, isNew
)

fun RPNewsItem.toNewsItem(): NewsItem =
    NewsItem(postId, postBody, postTime, senderPhoto, senderName, bgColor)
