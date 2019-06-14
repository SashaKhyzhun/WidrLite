package com.alexanderkhyzhun.widrlite.ui

import android.graphics.Bitmap
import com.alexanderkhyzhun.widrlite.data.storage.SignUpRepository
import com.alexanderkhyzhun.widrlite.domain.MainUseCase
import com.alexanderkhyzhun.widrlite.ui.mvp.BasePresenter
import com.arellomobile.mvp.InjectViewState
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
@InjectViewState
class MainPresenter : BasePresenter<MainView>(), KoinComponent {

    val useCase: MainUseCase by inject()

    fun savePhotoFromCamera(photo: Bitmap) {
        useCase.storeUserPhoto(photo)
    }

    fun savePhotoFromStorage(photo: Bitmap) {
        useCase.storeUserPhoto(photo)
    }

}