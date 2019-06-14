package com.alexanderkhyzhun.widrlite.ui.splash

import android.annotation.SuppressLint
import com.alexanderkhyzhun.widrlite.data.storage.AuthRepository
import com.alexanderkhyzhun.widrlite.ui.mvp.BasePresenter
import com.arellomobile.mvp.InjectViewState
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * @author SashaKhyzhun
 * Created on 4/26/19.
 */

@InjectViewState
@SuppressLint("CheckResult")
class SplashPresenter : BasePresenter<SplashView>(), KoinComponent {

    private val auth: AuthRepository by inject()

    fun handleUserAuthStatus() {
        if (auth.isUserAuthorized()) {
            redirectToNextPage()
        } else {
            redirectToLoginPage()
        }
    }

    private fun redirectToNextPage() {
        viewState.onRedirectToMainPage()
    }

    private fun redirectToLoginPage() {
        viewState.onRedirectToLoginPage()
    }

}