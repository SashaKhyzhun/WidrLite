package com.alexanderkhyzhun.widrlite.domain.impl

import android.annotation.SuppressLint
import android.graphics.Bitmap
import com.alexanderkhyzhun.widrlite.data.models.ChatItem
import com.alexanderkhyzhun.widrlite.data.models.NewsItem
import com.alexanderkhyzhun.widrlite.data.storage.CollectionRepository
import com.alexanderkhyzhun.widrlite.data.storage.SignUpRepository
import com.alexanderkhyzhun.widrlite.data.storage.StorageRepository
import com.alexanderkhyzhun.widrlite.domain.MainUseCase
import com.alexanderkhyzhun.widrlite.utils.bitmapToString

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
@SuppressLint("CheckResult")
class MainUseCaseImpl(
    private val storage: StorageRepository,
    private val signUp: SignUpRepository,
    private val collectionRepo: CollectionRepository
) : MainUseCase {


    override fun storeUserPhoto(bitmap: Bitmap) {
        signUp.photo().onNext(bitmap)

        signUp.photo()
            .map(::bitmapToString)
            .subscribe {
                storage.setPhoto(it)
            }
    }

    override fun updateSelectedConversation(item: NewsItem) {
        val chat = ChatItem(item.postId, item.authorName, item.authorImage, emptyList())
        collectionRepo.updateChatDetails(chat)
    }

}