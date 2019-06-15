package com.alexanderkhyzhun.widrlite.ui.feed.explore

import com.alexanderkhyzhun.widrlite.ui.mvp.ErrorView
import com.alexanderkhyzhun.widrlite.ui.mvp.LoadingView
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface ExploreView : MvpView, ErrorView, LoadingView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun renderView()

}
