package com.alexanderkhyzhun.widrlite.ui.feed.news

import com.alexanderkhyzhun.widrlite.data.models.NewsItem
import com.alexanderkhyzhun.widrlite.ui.mvp.ErrorView
import com.alexanderkhyzhun.widrlite.ui.mvp.LoadingView
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface NewsView : MvpView, ErrorView, LoadingView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun renderView(data: List<NewsItem>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onClickedShowMutual()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onClickedComment()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onClickedShare(postTitle: String, postDescription: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onClickedOffer(newsItem: NewsItem)


}
