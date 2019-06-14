package com.alexanderkhyzhun.widrlite.ui.messages

import com.alexanderkhyzhun.widrlite.data.models.MessageItem
import com.alexanderkhyzhun.widrlite.ui.mvp.ErrorView
import com.alexanderkhyzhun.widrlite.ui.mvp.LoadingView
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface MessagesView : MvpView, ErrorView, LoadingView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun renderMessages(data: List<MessageItem>)

}
