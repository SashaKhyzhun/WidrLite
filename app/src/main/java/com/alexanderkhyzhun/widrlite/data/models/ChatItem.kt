package com.alexanderkhyzhun.widrlite.data.models

/**
 * @author Alexander Khyzhun
 * Created on 15 June, 2019
 */
data class ChatItem(
    val userId: String,
    val userName: String,
    val userImage: String,
    val conversations: List<ConversationItem>?
)