package com.alexanderkhyzhun.widrlite.ui.chat

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
class ChatPresenter : BasePresenter<ChatView>(), KoinComponent {

    val useCase: ChatUseCase by inject()

    init {

    }



}