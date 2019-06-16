package com.alexanderkhyzhun.widrlite.ui.auth

import android.annotation.SuppressLint
import android.text.TextUtils
import android.util.Patterns
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.domain.AuthUseCase
import com.alexanderkhyzhun.widrlite.domain.modles.ValidationView
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseActivity
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseActivity.Companion.FIELD_CHECK_DELAY
import com.alexanderkhyzhun.widrlite.ui.mvp.BasePresenter
import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.subjects.PublishSubject
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.concurrent.TimeUnit

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
@SuppressLint("CheckResult")
@InjectViewState
class AuthPresenter : BasePresenter<AuthView>(), KoinComponent {

    val schedulers: Schedulers by inject()
    val useCase: AuthUseCase by inject()

    var buttonStatus = false

    private val firstNameFocusChangesSubject = PublishSubject.create<Boolean>()
    private val lastNameFocusChangesSubject = PublishSubject.create<Boolean>()
    private val phoneNumberFocusChangesSubject = PublishSubject.create<Boolean>()
    private val emailFocusChangesSubject = PublishSubject.create<Boolean>()
    private val passwordFocusChangesSubject = PublishSubject.create<Boolean>()


    init {
        val firstNameFieldState = Observables.combineLatest(
            useCase
                .firstName()
                .sample(FIELD_CHECK_DELAY, TimeUnit.MILLISECONDS, schedulers.computation())
                .map(CharSequence::toString), firstNameFocusChangesSubject
        ) { text, focus ->

            ValidationView.FirstNameCell(text, focus)
        }

        val lastNameFieldState = Observables.combineLatest(
            useCase
                .lastName()
                .sample(FIELD_CHECK_DELAY, TimeUnit.MILLISECONDS, schedulers.computation())
                .map(CharSequence::toString), lastNameFocusChangesSubject
        ) { text, focus ->

            ValidationView.LastNameCell(text, focus)
        }

        val phoneNumberFieldState = Observables.combineLatest(
            useCase
                .phoneNumber()
                .sample(FIELD_CHECK_DELAY, TimeUnit.MILLISECONDS, schedulers.computation())
                .map(CharSequence::toString), phoneNumberFocusChangesSubject
        ) { text, focus ->

            ValidationView.PhoneNumberCell(text, focus)
        }

        val emailFieldState = Observables.combineLatest(
            useCase
                .email()
                .sample(FIELD_CHECK_DELAY, TimeUnit.MILLISECONDS, schedulers.computation())
                .map(CharSequence::toString), emailFocusChangesSubject
        ) { text, focus ->

            ValidationView.EmailCell(text, focus)
        }

        val passwordFieldState = Observables.combineLatest(
            useCase
                .password()
                .sample(FIELD_CHECK_DELAY, TimeUnit.MILLISECONDS, schedulers.computation())
                .map(CharSequence::toString), passwordFocusChangesSubject
        ) { text, focus ->

            ValidationView.PasswordCell(text, focus)
        }

        val termsState = useCase.terms().map { ValidationView.TermsCell(it) }

        useCase.nextButton().subscribe {
            buttonStatus = it
            viewState.nextButtonState(it)
        }

        Observable.merge(
            listOf(
                firstNameFieldState,
                lastNameFieldState,
                phoneNumberFieldState,
                emailFieldState,
                passwordFieldState,
                termsState
            )
        )
            .scan(ValidationView.Form(), { currentState, newState ->
                when (newState) {
                    is ValidationView.FirstNameCell -> {
                        val firstNameValid = !TextUtils.isEmpty(newState.firstName)
                        val showFirstNameError = !firstNameValid

                        currentState.copy(
                            firstNameValid = firstNameValid,
                            firstNameError = showFirstNameError
                        )

                    }

                    is ValidationView.LastNameCell -> {
                        val lastNameValid = !TextUtils.isEmpty(newState.lastName)
                        val showLastNameError = !lastNameValid

                        currentState.copy(
                            lastNameValid = lastNameValid,
                            lastNameError = showLastNameError
                        )
                    }

                    is ValidationView.PhoneNumberCell -> {
//                        val phoneNumberValid =
//                            Pattern.matches(PHONE_NUMBER_REGEX, newState.phoneNumber)
                        val phoneNumberValid = true

                        val showNumberError = !phoneNumberValid
                        currentState.copy(
                            phoneNumberValid = phoneNumberValid,
                            phoneNumberError = showNumberError
                        )
                    }

                    is ValidationView.EmailCell -> {
                        val emailValid =
                            Patterns.EMAIL_ADDRESS.matcher(newState.email).matches()
                        val showEmailError = !emailValid

                        currentState.copy(
                            emailValid = emailValid,
                            emailError = showEmailError
                        )
                    }

                    is ValidationView.PasswordCell -> {
                        val passwordValid =
                            newState.password.length >= BaseActivity.MIN_PASSWORD_LENGTH
                        val showPasswordError = !passwordValid

                        currentState.copy(
                            passwordValid = passwordValid,
                            passwordError = showPasswordError
                        )
                    }

                    is ValidationView.TermsCell -> {
                        currentState.copy(
                            termsValid = newState.terms,
                            termsError = newState.terms.not()
                        )
                    }

                    else -> currentState
                }
            })
            .distinctUntilChanged()
            .compose(bindUntilDestroy())
            .subscribeOn(schedulers.computation())
            .observeOn(schedulers.mainThread())
            .subscribe(viewState::renderView, viewState::renderError)

    }

    fun onFirstNameTextChanges(text: CharSequence) {
        useCase.firstName().onNext(text)
    }

    fun onFirstNameFocusChanges(focused: Boolean) {
        firstNameFocusChangesSubject.onNext(focused)
    }


    fun onLastNameTextChanges(text: CharSequence) {
        useCase.lastName().onNext(text)
    }

    fun onLastNameFocusChanges(focused: Boolean) {
        lastNameFocusChangesSubject.onNext(focused)
    }


    fun onPhoneNumberTextChanges(text: CharSequence) {
        useCase.phoneNumber().onNext(text)
    }

    fun onPhoneNumberFocusChanges(focused: Boolean) {
        phoneNumberFocusChangesSubject.onNext(focused)
    }


    fun onEmailTextChanges(text: CharSequence) {
        useCase.email().onNext(text)
    }

    fun onEmailFocusChanges(focused: Boolean) {
        emailFocusChangesSubject.onNext(focused)
    }


    fun onPasswordTextChanges(text: CharSequence) {
        useCase.password().onNext(text)
    }

    fun onPasswordFocusChanges(focused: Boolean) {
        passwordFocusChangesSubject.onNext(focused)
    }

    fun onTermCheckChanges(checked: Boolean) {
        useCase.terms().onNext(checked)
    }

    fun onNextButtonReady(ready: Boolean) {
        useCase.nextButton().onNext(ready)
    }

    fun onClickSignUp() {
        useCase.signUpAccount()
            .compose(bindUntilDestroy())
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.mainThread())
            .doOnComplete { viewState.hideLoader() }
            .doOnError { viewState.hideLoader() }
            .doOnSubscribe { viewState.showLoader() }
            .subscribe({
                viewState.onAccountCreated()
            }, viewState::renderError)


    }


}