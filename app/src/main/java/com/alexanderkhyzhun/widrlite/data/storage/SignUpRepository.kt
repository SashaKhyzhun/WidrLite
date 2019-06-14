package com.alexanderkhyzhun.widrlite.data.storage

import android.graphics.Bitmap
import io.reactivex.subjects.BehaviorSubject

interface SignUpRepository {

    fun firstName(): BehaviorSubject<CharSequence>
    fun lastName(): BehaviorSubject<CharSequence>
    fun phoneNumber(): BehaviorSubject<CharSequence>
    fun email(): BehaviorSubject<CharSequence>
    fun password(): BehaviorSubject<CharSequence>
    fun photo(): BehaviorSubject<Bitmap>
    fun businessName(): BehaviorSubject<CharSequence>
    fun businessType(): BehaviorSubject<CharSequence>
    fun accountType(): BehaviorSubject<CharSequence>
    fun terms(): BehaviorSubject<Boolean>

    fun nextButton(): BehaviorSubject<Boolean>

    fun signUpAccount()

}