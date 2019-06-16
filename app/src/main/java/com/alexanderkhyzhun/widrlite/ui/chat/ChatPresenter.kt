package com.alexanderkhyzhun.widrlite.ui.chat

import android.annotation.SuppressLint
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.domain.ChatUseCase
import com.alexanderkhyzhun.widrlite.ui.mvp.BasePresenter
import com.arellomobile.mvp.InjectViewState
import io.reactivex.subjects.PublishSubject
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import timber.log.Timber

/**
 * @author Alexander Khyzhun
 * Created on 15 June, 2019
 */
@InjectViewState
@SuppressLint("CheckResult")
class ChatPresenter : BasePresenter<ChatView>(), KoinComponent {

    val schedulers: Schedulers by inject()
    val useCase: ChatUseCase by inject()

    private val inputFocusChangesSubject = PublishSubject.create<Boolean>()
    private val isConnectedSubj = PublishSubject.create<Boolean>()

    init {
        onConnectedToRoom(false)

        isConnectedSubj
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe {
                when (it) {
                    true -> viewState.hideLoader()
                    else -> viewState.showLoader()
                }
        }

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
                //...
            }, viewState::renderError)





        useCase.messageText()
            .compose(bindUntilDestroy())
            .map { it.trim() }
            .subscribeOn(schedulers.computation())
            .observeOn(schedulers.mainThread())
            .subscribe(viewState::renderCallSendIcon, viewState::renderError)

    }

    fun onMessageTextChanges(text: CharSequence) {
        useCase.messageText().onNext(text)
    }

    fun onMessageFocusChanges(focused: Boolean) {
        inputFocusChangesSubject.onNext(focused)
    }

    fun onClickSendOrCall(text: String) {
        text.trim()
        when (text.length) {
            0 -> viewState.onClickedCall()
            else -> {
                viewState.onClickedSend(text)
                viewState.clearEditText()
            }
        }
    }

    fun onConnectedToRoom(isConnected: Boolean) {
        isConnectedSubj.onNext(isConnected)
    }


}