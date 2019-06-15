package com.alexanderkhyzhun.widrlite.data.models

import com.alexanderkhyzhun.widrlite.ui.adapters.DisplayableItem

data class NewsItem(
    val postId: String,
    val postDescription: String,
    val postDate: String,
    val authorImage: String,
    val authorName: String,
    val bgColor: String
) : DisplayableItem
