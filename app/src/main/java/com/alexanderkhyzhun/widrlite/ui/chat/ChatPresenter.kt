package com.alexanderkhyzhun.widrlite.ui.chat

import android.annotation.SuppressLint
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.domain.ChatUseCase
import com.alexanderkhyzhun.widrlite.ui.mvp.BasePresenter
import com.arellomobile.mvp.InjectViewState
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * @author Alexander Khyzhun
 * Created on 15 June, 2019
 */
@InjectViewState
@SuppressLint("CheckResult")
class ChatPresenter : BasePresenter<ChatView>(), KoinComponent {

    val schedulers: Schedulers by inject()
    val useCase: ChatUseCase by inject()

    init {
        useCase.fetchChatData()
            .compose(bindUntilDestroy())
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.mainThread())
            .subscribe(viewState::renderView, viewState::renderError)

        useCase.fetchMessages()
            .compose(bindUntilDestroy())
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.mainThread())
            .doOnComplete { viewState.hideLoader() }
            .doOnError { viewState.hideLoader() }
            .doOnSubscribe { viewState.showLoader() }
            .subscribe({

                //viewState.onAccountCreated()

            }, viewState::renderError)
    }





}