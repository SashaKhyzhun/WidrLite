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
import java.util.concurrent.TimeUnit

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class ChatActivity : BaseActivity(), ChatView, RoomListener {

    val schedulers: Schedulers by inject()
    val glideManager: RequestManager by inject()

    @InjectPresenter
    lateinit var presenter: ChatPresenter

    private lateinit var scaledrone: Scaledrone
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messagesView: ListView

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
            .subscribe {
                presenter.onClickSendOrCall(item_chat_bottom_panel_et_input.text.toString())
            }


        messageAdapter = MessageAdapter(this)
        messagesView = findViewById<View>(R.id.activity_chat_list_view) as ListView
        messagesView.adapter = messageAdapter

        val data = MemberData(getRandomName(), getRandomColor())

        scaledrone = Scaledrone(channelID, data)
        scaledrone.connect(object : Listener {
            override fun onOpen() {
                println("Scaledrone connection open")
                scaledrone.subscribe(roomName, this@ChatActivity)
            }

            override fun onOpenFailure(ex: Exception) {
                System.err.println(ex)
            }

            override fun onFailure(ex: Exception) {
                System.err.println(ex)
            }

            override fun onClosed(reason: String) {
                System.err.println(reason)
            }
        })

    }

    override fun onClickedSend(text: String) {
        scaledrone.publish(roomName, text)
        item_chat_bottom_panel_et_input.text.clear()
    }

    override fun onOpen(room: Room) {
        println("Conneted to room")
    }

    override fun onOpenFailure(room: Room, ex: Exception) {
        System.err.println(ex)
    }

    override fun onMessage(room: Room, receivedMessage: com.scaledrone.lib.Message) {
        val mapper = ObjectMapper()
        try {
            val data = mapper.treeToValue(receivedMessage.member.clientData, MemberData::class.java)
            val belongsToCurrentUser = receivedMessage.clientID == scaledrone.clientID
            val message = Message(receivedMessage.data.asText(), data, belongsToCurrentUser)

            runOnUiThread {
                messageAdapter.add(message)
                messagesView.setSelection(messagesView.count - 1)
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