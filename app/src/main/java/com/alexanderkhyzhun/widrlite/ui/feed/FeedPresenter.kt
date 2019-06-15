package com.alexanderkhyzhun.widrlite.ui.feed

import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.domain.FeedUseCase
import com.alexanderkhyzhun.widrlite.ui.mvp.BasePresenter
import com.arellomobile.mvp.InjectViewState
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * @author Alexander Khyzhun
 * Created on 15 June, 2019
 */
@InjectViewState
class FeedPresenter : BasePresenter<FeedView>(), KoinComponent {

    val schedulers: Schedulers by inject()
    val useCase: FeedUseCase by inject()

}