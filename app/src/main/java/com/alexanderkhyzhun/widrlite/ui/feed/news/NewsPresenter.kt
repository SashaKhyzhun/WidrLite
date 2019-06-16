package com.alexanderkhyzhun.widrlite.ui.feed.news

import android.annotation.SuppressLint
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.data.models.NewsItem
import com.alexanderkhyzhun.widrlite.domain.NewsUseCase
import com.alexanderkhyzhun.widrlite.ui.adapters.DisplayableItem
import com.alexanderkhyzhun.widrlite.ui.mvp.BasePresenter
import com.arellomobile.mvp.InjectViewState
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import timber.log.Timber

/**
 * @author Alexander Khyzhun
 * Created on 15 June, 2019
 */
@InjectViewState
@SuppressLint("CheckResult")
class NewsPresenter : BasePresenter<NewsView>(), KoinComponent {

    val schedulers: Schedulers by inject()
    val useCase: NewsUseCase by inject()

    init {
        useCase.fetchNewsFeed()
            .compose(bindUntilDestroy())
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.mainThread())
            .doOnComplete { viewState.hideLoader() }
            .doOnError { viewState.hideLoader() }
            .doOnSubscribe { viewState.showLoader() }
            .subscribe(viewState::renderView, viewState::renderError)
    }


    fun onPostClick(item: DisplayableItem) {
        item as NewsItem
        //...
    }

    fun onMutualClick(item: DisplayableItem) {
        viewState.onClickedShowMutual()
    }

    fun onCommentClick(item: DisplayableItem) {
        viewState.onClickedComment()
    }

    fun onShareClick(item: DisplayableItem) {
        item as NewsItem
        viewState.onClickedShare(item.postTitle, item.postDescription)
    }

    fun onOfferClick(item: DisplayableItem) {
        viewState.onClickedOffer(item as NewsItem)
    }


}