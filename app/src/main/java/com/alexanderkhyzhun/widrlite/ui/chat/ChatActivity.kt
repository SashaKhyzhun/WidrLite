package com.alexanderkhyzhun.widrlite.ui.chat

import android.annotation.SuppressLint
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
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.view.focusChanges
import com.jakewharton.rxbinding2.widget.textChanges
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.item_chat_bottom_panel.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class ChatActivity : BaseActivity(), ChatView {

    val schedulers: Schedulers by inject()
    val glideManager: RequestManager by inject()

    @InjectPresenter
    lateinit var presenter: ChatPresenter


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        activity_chat_layout_back.clicks()
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { finish() }

        item_chat_bottom_panel_layout_contact.clicks()
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { toast("Contact") }

        item_chat_bottom_panel_layout_image.clicks()
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { toast("Image") }

        item_chat_bottom_panel_layout_contact.clicks()
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { toast("contact") }

        item_chat_bottom_panel_et_input.focusChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onMessageFocusChanges)

        item_chat_bottom_panel_et_input.textChanges()
            .skipInitialValue()
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe(presenter::onMessageTextChanges)

        item_chat_bottom_panel_iv_call_send.clicks()
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { presenter.onClickSendOrCall() }
    }

    override fun onClickedSend(text: String) {
        //...
    }

    override fun renderView(chat: ChatItem) {
        glideManager
            .load(chat.userImage)
            .apply(RequestOptions().circleCrop())
            .into(activity_chat_iv_user_photo)

        activity_chat_tv_user_name.text = chat.userName
    }

    override fun renderCallSendIcon(text: CharSequence) {
        when (text.length) {
            0 -> item_chat_bottom_panel_iv_call_send.setBackgroundResource(R.drawable.ic_call)
            else -> item_chat_bottom_panel_iv_call_send.setBackgroundResource(R.drawable.ic_send)
        }
    }

    override fun onClickedCall() {
        toast("Call")
    }

    override fun showLoader() {
        /* code implementation */
    }

    override fun hideLoader() {
        /* code implementation */
    }

    override fun renderMessage(text: String) {
        showSnack(text)
    }

    override fun renderError(throwable: Throwable) {
        showSnack(throwable.message)
    }


    companion object {
        const val TAG = "ChatActivity"
        private const val channelID = "xLrhvoc0sDBIzbgf"
        private const val roomName = "observable-room"

        fun getIntent(context: Context?) = Intent(context, ChatActivity::class.java)
    }
}