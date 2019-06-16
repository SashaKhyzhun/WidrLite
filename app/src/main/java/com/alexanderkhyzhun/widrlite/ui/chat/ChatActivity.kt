package com.alexanderkhyzhun.widrlite.ui.chat

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.data.models.ChatItem
import com.alexanderkhyzhun.widrlite.ui.adapters.MessageAdapter
import com.alexanderkhyzhun.widrlite.ui.adapters.models.MemberData
import com.alexanderkhyzhun.widrlite.ui.adapters.models.Message
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseActivity
import com.alexanderkhyzhun.widrlite.utils.getRandomColor
import com.alexanderkhyzhun.widrlite.utils.getRandomName
import com.alexanderkhyzhun.widrlite.utils.setGone
import com.alexanderkhyzhun.widrlite.utils.setVisible
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.view.focusChanges
import com.jakewharton.rxbinding2.widget.textChanges
import com.scaledrone.lib.Listener
import com.scaledrone.lib.Room
import com.scaledrone.lib.RoomListener
import com.scaledrone.lib.Scaledrone
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.item_chat_bottom_panel.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class ChatActivity : BaseActivity(), ChatView, RoomListener, Listener {

    val schedulers: Schedulers by inject()
    val glideManager: RequestManager by inject()

    @InjectPresenter
    lateinit var presenter: ChatPresenter

    private lateinit var scaledrone: Scaledrone
    private lateinit var messageAdapter: MessageAdapter

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        scaledrone = Scaledrone(channelID, MemberData(getRandomName(), getRandomColor()))
        scaledrone.connect(this)

        messageAdapter = MessageAdapter(this)
        activity_chat_list_view.adapter = messageAdapter

        /**
         * Back button
         */
        activity_chat_layout_back.clicks()
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { finish() }

        /**
         * Contact button
         */
        item_chat_bottom_panel_layout_contact.clicks()
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { toast("Contact") }

        item_chat_bottom_panel_iv_contact.clicks()
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { toast("contact") }

        /**
         * Image button
         */
        item_chat_bottom_panel_layout_image.clicks()
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { toast("Image") }

        item_chat_bottom_panel_iv_image.clicks()
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { toast("Image") }

        /**
         * Make a photo image
         */
        item_chat_bottom_panel_layout_photo.clicks()
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { toast("Make a photo") }

        item_chat_bottom_panel_iv_photo.clicks()
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { toast("Make a photo") }

        /**
         * Edit Text
         */
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

        /**
         * Send a message or make a call button
         */
        item_chat_bottom_panel_layout_call_send.clicks()
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe {
                presenter.onClickSendOrCall(item_chat_bottom_panel_et_input.text.toString())
            }

        item_chat_bottom_panel_iv_call_send.clicks()
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe {
                presenter.onClickSendOrCall(item_chat_bottom_panel_et_input.text.toString())
            }

        /**
         * User Photo
         */
        activity_chat_iv_user_photo.clicks()
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { toast("Profile") }

        /**
         * Mutual friends
         */
        activity_chat_tv_display_mutual_contact.clicks()
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { toast("Show mutual contacts") }

        /**
         * Terms or premium
         */
        activity_chat_layout_premium.clicks()
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { toast("Droit des sociétés") }

    }


    override fun onClickedSend(text: String) {
        scaledrone.publish(roomName, text)
        item_chat_bottom_panel_et_input.text.clear()
    }


    override fun onMessage(room: Room, receivedMessage: com.scaledrone.lib.Message) {
        val mapper = ObjectMapper()
        try {
            val data = mapper.treeToValue(
                receivedMessage.member.clientData,
                MemberData::class.java
            )
            val belongsToCurrentUser = receivedMessage.clientID == scaledrone.clientID
            val message = Message(receivedMessage.data.asText(), data, belongsToCurrentUser)

            runOnUiThread {
                messageAdapter.add(message)
                activity_chat_list_view.setSelection(activity_chat_list_view.count - 1)
            }

        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }

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

    override fun onOpen() {
        scaledrone.subscribe(roomName, this@ChatActivity)
    }

    override fun clearEditText() {
        item_chat_bottom_panel_et_input.text.clear()
    }

    override fun onOpen(room: Room) {
        Timber.d("Connected to room")
        presenter.onConnectedToRoom(true)
    }

    override fun onOpenFailure(room: Room, ex: Exception) {
        Timber.e(ex)
    }

    override fun onOpenFailure(ex: Exception) {
        Timber.e(ex)
    }

    override fun onFailure(ex: Exception) {
        Timber.e(ex)
    }

    override fun onClosed(reason: String) {
        Timber.d(reason)
    }

    override fun onClickedCall() {
        toast("Call")
    }

    override fun showLoader() {
        preloader_layout.setVisible()
    }

    override fun hideLoader() {
        preloader_layout.setGone()
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