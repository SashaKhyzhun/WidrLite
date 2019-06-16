package com.alexanderkhyzhun.widrlite.ui.feed.explore

import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.domain.ExploreUseCase
import com.alexanderkhyzhun.widrlite.ui.mvp.BasePresenter
import com.arellomobile.mvp.InjectViewState
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

@InjectViewState
class ExplorePresenter : BasePresenter<ExploreView>(), KoinComponent {

    val schedulers: Schedulers by inject()
    val useCase: ExploreUseCase by inject()

}
