package com.alexanderkhyzhun.widrlite.data.models

import com.alexanderkhyzhun.widrlite.ui.adapters.DisplayableItem

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
data class NotificationItem(
    val id: String,
    val image: String,
    val name: String,
    val message: String,
    val `when`: String,
    val isNew: Boolean
) : DisplayableItem