package com.alexanderkhyzhun.widrlite.ui.auth

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.app.WidrLiteApp.Companion.context
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.domain.modles.ValidationView
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseActivity
import com.alexanderkhyzhun.widrlite.ui.splash.SplashActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.view.focusChanges
import com.jakewharton.rxbinding2.widget.checkedChanges
import com.jakewharton.rxbinding2.widget.textChanges
import kotlinx.android.synthetic.main.activity_auth.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
@SuppressLint("CheckResult")
class AuthActivity : BaseActivity(), AuthView {

    val schedulers: Schedulers by inject()

    @InjectPresenter
    lateinit var presenter: AuthPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)


        activity_auth_et_first_name.textChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onFirstNameTextChanges)

        activity_auth_et_first_name.focusChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onFirstNameFocusChanges)



        activity_auth_et_last_name.textChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onLastNameTextChanges)

        activity_auth_et_last_name.focusChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onLastNameFocusChanges)



        activity_auth_et_phone_number.textChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onPhoneNumberTextChanges)

        activity_auth_et_phone_number.focusChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onPhoneNumberFocusChanges)



        activity_auth_et_email.textChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onEmailTextChanges)

        activity_auth_et_email.focusChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onEmailFocusChanges)



        activity_auth_et_password.textChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onPasswordTextChanges)

        activity_auth_et_password.focusChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onPasswordFocusChanges)



        activity_auth_checked_tv.checkedChanges()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onTermCheckChanges)


        activity_auth_btn_next.clicks()
            .filter { presenter.buttonStatus }
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { presenter.onClickSignUp() }


        setupMultipleClickableLinks()
    }

    override fun renderView(viewState: ValidationView.Form) {
        activity_auth_et_first_name.error = when (viewState.firstNameError) {
            true -> resources.getString(R.string.auth_error_first_name)
            false -> null
        }

        activity_auth_et_last_name.error = when (viewState.lastNameError) {
            true -> resources.getString(R.string.auth_error_last_name)
            false -> null
        }

        activity_auth_et_phone_number.error = when (viewState.phoneNumberError) {
            true -> resources.getString(R.string.auth_error_phone_number)
            false -> null
        }

        activity_auth_et_email.error = when (viewState.emailError) {
            true -> resources.getString(R.string.auth_error_email)
            false -> null
        }

        activity_auth_et_password.error = when (viewState.passwordError) {
            true -> resources.getString(R.string.auth_error_password)
            false -> null
        }

        activity_auth_tv_terms_error.visibility = when (viewState.termsError) {
            true -> View.VISIBLE
            false -> View.INVISIBLE
        }


        presenter.onNextButtonReady(
            viewState.firstNameValid
                    && viewState.lastNameValid
                    && viewState.phoneNumberValid
                    && viewState.emailValid
                    && viewState.passwordValid
                    && viewState.termsValid
        )
    }

    override fun nextButtonState(ready: Boolean) {
        when (ready) {
            true -> {
                activity_auth_btn_next.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.primary
                    )
                )
            }
            false -> {
                activity_auth_btn_next.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.inactive
                    )
                )
            }
        }
    }

    override fun onAccountCreated() {
        startActivity(SplashActivity.getIntent(this))
    }

    override fun renderError(throwable: Throwable) {

    }

    override fun renderMessage(text: String) {

    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    /**
     * By continuing you are indicating that you have read and
     * agree to the [Terms of Service] and [Privacy Policy]
     */
    private fun setupMultipleClickableLinks() {
        val spanTxt = SpannableStringBuilder(
            "By continuing you are indicating that you have read and agree to the "
        )
        spanTxt.append("Terms of Service")
        spanTxt.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                toast("Terms of services Clicked")
            }
        }, spanTxt.length - "Terms of Service".length, spanTxt.length, 0)

        spanTxt.append(" and ")
        spanTxt.append("Privacy Policy")
        spanTxt.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                toast("Privacy Policy Clicked")
            }
        }, spanTxt.length - "Privacy Policy".length, spanTxt.length, 0)

        activity_auth_tv_terms_and_privacy.movementMethod = LinkMovementMethod.getInstance()
        activity_auth_tv_terms_and_privacy.setText(spanTxt, TextView.BufferType.SPANNABLE)
    }

    companion object {
        const val TAG = "AuthActivity"
        fun getIntent(context: Context?) = Intent(context, AuthActivity::class.java)
    }
}