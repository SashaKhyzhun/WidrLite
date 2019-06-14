package com.alexanderkhyzhun.widrlite.data.models

import com.alexanderkhyzhun.widrlite.ui.adapters.DisplayableItem

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
data class MessageItem(
    val senderId: String,
    val senderPhoto: String,
    val senderName: String,
    val senderLocation: String,
    val senderJob: String,
    val body: String,
    val date: String,
    val amount: String,
    val isNew: Boolean
) : DisplayableItem