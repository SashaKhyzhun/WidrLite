package com.alexanderkhyzhun.widrlite.ui.feed.people

import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.domain.PeopleUseCase
import com.alexanderkhyzhun.widrlite.ui.mvp.BasePresenter
import com.arellomobile.mvp.InjectViewState
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

@InjectViewState
class PeoplePresenter : BasePresenter<PeopleView>(), KoinComponent {

    val schedulers: Schedulers by inject()
    val useCase: PeopleUseCase by inject()

}
