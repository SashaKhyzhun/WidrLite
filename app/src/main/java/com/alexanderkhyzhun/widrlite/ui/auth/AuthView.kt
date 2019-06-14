package com.alexanderkhyzhun.widrlite.ui.auth

import com.alexanderkhyzhun.widrlite.domain.modles.ValidationView
import com.alexanderkhyzhun.widrlite.ui.mvp.ErrorView
import com.alexanderkhyzhun.widrlite.ui.mvp.LoadingView
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface AuthView : MvpView, ErrorView, LoadingView {


    @StateStrategyType(AddToEndSingleStrategy::class)
    fun renderView(viewState: ValidationView.Form)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun nextButtonState(ready: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onAccountCreated()

}
