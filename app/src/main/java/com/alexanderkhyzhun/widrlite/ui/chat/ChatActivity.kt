package com.alexanderkhyzhun.widrlite.ui.chat

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.data.models.ChatItem
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_chat.*
import org.koin.android.ext.android.inject

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class ChatActivity : BaseActivity(), ChatView {

    val schedulers: Schedulers by inject()
    val glideManager: RequestManager by inject()

    @InjectPresenter
    lateinit var presenter: ChatPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
    }

    override fun renderView(chat: ChatItem) {
        glideManager
            .load(chat.userImage)
            .apply(RequestOptions().circleCrop())
            .into(activity_chat_iv_user_photo)

        activity_chat_tv_user_name.text = chat.userName
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


    companion object {
        const val TAG = "ChatActivity"
        fun getIntent(context: Context?) = Intent(context, ChatActivity::class.java)
    }
}