package com.alexanderkhyzhun.widrlite.ui.notifications

import android.annotation.SuppressLint
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.domain.NotificationsUseCase
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
class NotificationsPresenter : BasePresenter<NotificationsView>(), KoinComponent {

    val schedulers: Schedulers by inject()
    val useCase: NotificationsUseCase by inject()


    init {
        useCase.fetchNotification()
            .compose(bindUntilDestroy())
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.mainThread())
            .doOnComplete { viewState.hideLoader() }
            .doOnError { viewState.hideLoader() }
            .doOnSubscribe { viewState.showLoader() }
            .subscribe({
                viewState.renderNotifications(it)
            }, viewState::renderError)
    }


    fun onNotificationClick(item: DisplayableItem) {

    }



}