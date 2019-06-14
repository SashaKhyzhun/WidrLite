package com.alexanderkhyzhun.widrlite.ui.conversations

import android.annotation.SuppressLint
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.data.models.ConversationItem
import com.alexanderkhyzhun.widrlite.data.models.UserItem
import com.alexanderkhyzhun.widrlite.domain.ConversationUseCase
import com.alexanderkhyzhun.widrlite.ui.adapters.DisplayableItem
import com.alexanderkhyzhun.widrlite.ui.mvp.BasePresenter
import com.alexanderkhyzhun.widrlite.utils.empty
import com.arellomobile.mvp.InjectViewState
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import timber.log.Timber

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
@InjectViewState
@SuppressLint("CheckResult")
class ConversationsPresenter : BasePresenter<ConversationsView>(), KoinComponent {

    val schedulers: Schedulers by inject()
    val useCase: ConversationUseCase by inject()

    init {
        useCase.fetchConversations()
            .compose(bindUntilDestroy())
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.mainThread())
            .doOnComplete { viewState.hideLoader() }
            .doOnError { viewState.hideLoader() }
            .doOnSubscribe { viewState.showLoader() }
            .subscribe({
                viewState.renderConversations(it)
            }, viewState::renderError)
    }

    fun onMessageClick(item: DisplayableItem) {
        Timber.d("item = $item")
        item as ConversationItem

        useCase.updateSelectedConversation(
            UserItem(item.senderId, item.senderName, item.senderPhoto, emptyList())
        )

        viewState.onClickedOpenChat()
    }


}