package com.alexanderkhyzhun.widrlite.ui.profile

import android.graphics.Bitmap
import com.alexanderkhyzhun.widrlite.ui.mvp.ErrorView
import com.alexanderkhyzhun.widrlite.ui.mvp.LoadingView
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
interface ProfileView : MvpView, ErrorView, LoadingView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun renderName(userName: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun renderImage(image: Bitmap)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun renderImage(image: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onClickedTakePhoto()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onClickedImport()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onClickedSave()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onClickedProposal()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun savePhotoFromCamera(photo: Bitmap)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun savePhotoFromStorage(photo: Bitmap)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onClickedBurger()

}