package com.alexanderkhyzhun.widrlite.domain

import android.graphics.Bitmap
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import okhttp3.ResponseBody

interface AuthUseCase {

    fun firstName(): BehaviorSubject<CharSequence>
    fun lastName(): BehaviorSubject<CharSequence>
    fun phoneNumber(): BehaviorSubject<CharSequence>
    fun email(): BehaviorSubject<CharSequence>
    fun password(): BehaviorSubject<CharSequence>
    fun terms(): BehaviorSubject<Boolean>
    fun nextButton(): BehaviorSubject<Boolean>

    fun photo(): BehaviorSubject<Bitmap>

    fun signUpAccount(): Observable<Boolean>

}
