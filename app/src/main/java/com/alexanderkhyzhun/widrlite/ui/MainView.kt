package com.alexanderkhyzhun.widrlite.ui

import com.alexanderkhyzhun.widrlite.ui.mvp.ErrorView
import com.alexanderkhyzhun.widrlite.ui.mvp.LoadingView
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */

interface MainView : MvpView, ErrorView, LoadingView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onPanelClickedShare(postTitle: String, postDescription: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onPanelClickedFacebook()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onPanelClickedSendRecommendation()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onPanelClose()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openConversation()

}