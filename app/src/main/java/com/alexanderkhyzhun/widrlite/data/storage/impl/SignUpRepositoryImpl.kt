package com.alexanderkhyzhun.widrlite.data.storage.impl

import android.graphics.Bitmap
import com.alexanderkhyzhun.widrlite.data.storage.SignUpRepository
import com.alexanderkhyzhun.widrlite.data.storage.StorageRepository
import io.reactivex.rxkotlin.Observables
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import java.util.*

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class SignUpRepositoryImpl(
    private val storage: StorageRepository
) : SignUpRepository {

    private val firstNameChangesSubject = BehaviorSubject.create<CharSequence>()
    private val lastNameChangesSubject = BehaviorSubject.create<CharSequence>()
    private val phoneNumberChangesSubject = BehaviorSubject.create<CharSequence>()
    private val emailChangesSubject = BehaviorSubject.create<CharSequence>()
    private val passwordChangesSubject = BehaviorSubject.create<CharSequence>()
    private val photoSubject = BehaviorSubject.create<Bitmap>()
    private val businessNameSubject = BehaviorSubject.create<CharSequence>()
    private val businessTypeSubject = BehaviorSubject.create<CharSequence>()
    private val accountTypeSubject = BehaviorSubject.create<CharSequence>()
    private val termsSubject = BehaviorSubject.create<Boolean>()
    private val nextButtonSubject = BehaviorSubject.create<Boolean>()


    override fun firstName(): BehaviorSubject<CharSequence> = firstNameChangesSubject

    override fun lastName(): BehaviorSubject<CharSequence> = lastNameChangesSubject

    override fun phoneNumber(): BehaviorSubject<CharSequence> = phoneNumberChangesSubject

    override fun email(): BehaviorSubject<CharSequence> = emailChangesSubject

    override fun password(): BehaviorSubject<CharSequence> = passwordChangesSubject

    override fun photo(): BehaviorSubject<Bitmap> = photoSubject

    override fun businessName(): BehaviorSubject<CharSequence> = businessNameSubject

    override fun businessType(): BehaviorSubject<CharSequence> = businessTypeSubject

    override fun accountType(): BehaviorSubject<CharSequence> = accountTypeSubject

    override fun terms(): BehaviorSubject<Boolean> = termsSubject

    override fun nextButton(): BehaviorSubject<Boolean> = nextButtonSubject

    override fun signUpAccount() {
        val firstNameObs = firstName().map(CharSequence::toString)
        val lastNameObs = lastName().map(CharSequence::toString)
        val phoneNumberObs = phoneNumber().map(CharSequence::toString)
        val emailObs = email().map(CharSequence::toString)
        val passwordObs = password().map(CharSequence::toString)
        val photoObs = photo()

        Observables.zip(
            firstNameObs,
            lastNameObs,
            phoneNumberObs,
            emailObs,
            passwordObs,
            photoObs
        ) { firstName, lastName, phone, email, password, photo ->

            with(storage) {
                setFirstName(firstName)
                setLastName(lastName)
                setPhoneNumber(phone)
                setEmail(email)
                setPassword(password)
                setPhoneNumber(phone)
            }
        }
    }
}