package com.alexanderkhyzhun.widrlite.domain

import android.graphics.Bitmap
import io.reactivex.subjects.BehaviorSubject

/**
 * @author SashaKhyzhun
 * Created on 4/26/19.
 */
interface ProfileUseCase {

    fun fetchUserName(): String?
    fun fetchUserImage(): Bitmap?

    fun savePhoto(photo: Bitmap)

    fun userPhoto(): BehaviorSubject<Bitmap>

}