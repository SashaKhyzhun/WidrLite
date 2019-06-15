package com.alexanderkhyzhun.widrlite.domain

import android.graphics.Bitmap
import com.alexanderkhyzhun.widrlite.data.models.NewsItem

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
interface MainUseCase {

    fun storeUserPhoto(bitmap: Bitmap)
    fun updateSelectedConversation(item: NewsItem)

}