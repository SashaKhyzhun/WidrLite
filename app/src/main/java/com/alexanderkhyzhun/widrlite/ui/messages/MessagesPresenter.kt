package com.alexanderkhyzhun.widrlite.ui.messages

import android.annotation.SuppressLint
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.domain.MessagesUseCase
import com.alexanderkhyzhun.widrlite.ui.adapters.DisplayableItem
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
class MessagesPresenter : BasePresenter<MessagesView>(), KoinComponent {

    val schedulers: Schedulers by inject()
    val useCase: MessagesUseCase by inject()

    init {
        useCase.fetchMessages()
            .compose(bindUntilDestroy())
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.mainThread())
            .doOnComplete { viewState.hideLoader() }
            .doOnError { viewState.hideLoader() }
            .doOnSubscribe { viewState.showLoader() }
            .subscribe({
                viewState.renderMessages(it)
            }, viewState::renderError)
    }

    fun onMessageClick(item: DisplayableItem) {

    }


}