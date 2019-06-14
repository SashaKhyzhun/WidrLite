package com.alexanderkhyzhun.widrlite.domain.impl

import android.graphics.Bitmap
import com.alexanderkhyzhun.widrlite.data.storage.AuthRepository
import com.alexanderkhyzhun.widrlite.data.storage.SignUpRepository
import com.alexanderkhyzhun.widrlite.data.storage.StorageRepository
import com.alexanderkhyzhun.widrlite.domain.ProfileUseCase
import com.alexanderkhyzhun.widrlite.utils.bitmapToString
import com.alexanderkhyzhun.widrlite.utils.stringToBitmap
import io.reactivex.subjects.BehaviorSubject

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class ProfileUseCaseImpl(
    private val storage: StorageRepository,
    private val signUp: SignUpRepository
) : ProfileUseCase {

    override fun fetchUserName(): String? {
        return storage.getFirstName()?.let { it } + storage.getLastName()?.let { it }
    }

    override fun fetchUserImage(): Bitmap? {
        return storage.getPhoto()?.let { stringToBitmap(it) }
    }

    override fun savePhoto(photo: Bitmap) {
        storage.setPhoto(bitmapToString(photo))
    }

    override fun userPhoto(): BehaviorSubject<Bitmap> = signUp.photo()

}