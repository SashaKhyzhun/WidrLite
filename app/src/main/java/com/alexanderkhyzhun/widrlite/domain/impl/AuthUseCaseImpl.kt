package com.alexanderkhyzhun.widrlite.domain.impl

import android.graphics.Bitmap
import com.alexanderkhyzhun.widrlite.data.Api
import com.alexanderkhyzhun.widrlite.data.storage.SignUpRepository
import com.alexanderkhyzhun.widrlite.domain.AuthUseCase
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import okhttp3.MediaType
import okhttp3.ResponseBody

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class AuthUseCaseImpl(
    private val signUpRepository: SignUpRepository,
    private val api: Api
) : AuthUseCase {


    override fun firstName(): BehaviorSubject<CharSequence> = signUpRepository.firstName()

    override fun lastName(): BehaviorSubject<CharSequence> = signUpRepository.lastName()

    override fun phoneNumber(): BehaviorSubject<CharSequence> = signUpRepository.phoneNumber()

    override fun email(): BehaviorSubject<CharSequence> = signUpRepository.email()

    override fun password(): BehaviorSubject<CharSequence> = signUpRepository.password()

    override fun terms(): BehaviorSubject<Boolean> = signUpRepository.terms()

    override fun photo(): BehaviorSubject<Bitmap> = signUpRepository.photo()

    override fun nextButton(): BehaviorSubject<Boolean> = signUpRepository.nextButton()

    override fun signUpAccount(): Observable<Boolean> = signUpRepository.signUpAccount()

}