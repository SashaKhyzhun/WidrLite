package com.alexanderkhyzhun.widrlite.ui.feed.people

import com.alexanderkhyzhun.widrlite.ui.mvp.ErrorView
import com.alexanderkhyzhun.widrlite.ui.mvp.LoadingView
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface PeopleView : MvpView, ErrorView, LoadingView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun renderView()

}