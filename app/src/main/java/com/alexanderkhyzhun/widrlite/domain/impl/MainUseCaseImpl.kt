package com.alexanderkhyzhun.widrlite.domain.impl

import android.annotation.SuppressLint
import android.graphics.Bitmap
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
    private val signUp: SignUpRepository
) : MainUseCase {


    override fun storeUserPhoto(bitmap: Bitmap) {
        signUp.photo().onNext(bitmap)

        signUp.photo()
            .map(::bitmapToString)
            .subscribe {
                storage.setPhoto(it)
            }

    }
}