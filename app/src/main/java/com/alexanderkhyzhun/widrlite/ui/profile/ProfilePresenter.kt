package com.alexanderkhyzhun.widrlite.ui.profile

import android.annotation.SuppressLint
import android.graphics.Bitmap
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.domain.ProfileUseCase
import com.alexanderkhyzhun.widrlite.ui.mvp.BasePresenter
import com.arellomobile.mvp.InjectViewState
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
@InjectViewState
@SuppressLint("CheckResult")
class ProfilePresenter : BasePresenter<ProfileView>(), KoinComponent {

    val schedulers: Schedulers by inject()
    val useCase: ProfileUseCase by inject()

    init {
        fetchUserName()
        fetchUserImage()

        useCase.userPhoto().subscribe {
            viewState.renderImage(it)
        }
    }

    private fun fetchUserName() {
        useCase.fetchUserName()?.let {
            viewState.renderName(it)
        }
    }

    private fun fetchUserImage() {
        useCase.fetchUserImage()?.let {
            viewState.renderImage(it)
        }
    }

    fun onClickProposal() {
        viewState.onClickedProposal()
    }

    fun onClickTakePhoto() {
        viewState.onClickedTakePhoto()
    }

    fun onClickImport() {
        viewState.onClickedImport()
    }

    fun savePhoto(photo: Bitmap) {
        useCase.savePhoto(photo)
    }

    fun onClickBurger() {
        viewState.onClickedBurger()
    }

}