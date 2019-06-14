package com.alexanderkhyzhun.widrlite.ui.chat

import android.app.Activity
import android.os.Bundle
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import org.koin.android.ext.android.inject

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class ChatActivity : BaseActivity(), ChatView {

    val schedulers: Schedulers by inject()

    @InjectPresenter
    lateinit var presenter: ChatPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
    }

    override fun renderView() {

    }

    override fun renderError(throwable: Throwable) {
        showSnack(throwable.message)
    }

    override fun renderMessage(text: String) {
        showSnack(text)
    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }
}