package com.alexanderkhyzhun.widrlite.ui.chat

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.data.models.ChatItem
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_chat.*
import org.koin.android.ext.android.inject
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import android.view.ViewTreeObserver
import com.alexanderkhyzhun.widrlite.utils.setGone
import com.alexanderkhyzhun.widrlite.utils.setVisible
import com.alexanderkhyzhun.widrlite.views.RevealBackgroundView

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

//        setupRevealBackground(savedInstanceState)
    }

//    private fun setupRevealBackground(savedInstanceState: Bundle?) {
//        vRevealBackground.setOnStateChangeListener(this)
//        if (savedInstanceState == null) {
//            val startingLocation = intent.getIntArrayExtra(ARG_REVEAL_START_LOCATION)
//            vRevealBackground.viewTreeObserver
//                .addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
//                    override fun onPreDraw(): Boolean {
//                        vRevealBackground.viewTreeObserver.removeOnPreDrawListener(this)
//                        vRevealBackground.startFromLocation(startingLocation)
//                        return false
//                    }
//                })
//        } else {
//            vRevealBackground.setToFinishedFrame()
//        }
//    }

//    override fun onStateChange(state: Int) {
//        if (RevealBackgroundView.STATE_FINISHED == state) {
//            activity_chat_layout_content.setVisible()
//        } else {
//            activity_chat_layout_content.setGone()
//        }
//    }

    override fun renderView(chat: ChatItem) {
        glideManager
            .load(chat.userImage)
            .apply(RequestOptions().circleCrop())
            .into(activity_chat_iv_user_photo)

        activity_chat_tv_user_name.text = chat.userName
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
        const val ARG_REVEAL_START_LOCATION = "reveal_start_location"

        fun getIntent(context: Context?) = Intent(context, ChatActivity::class.java)

        fun startUserProfileFromLocation(startingLocation: IntArray, startingActivity: Activity) {
            val intent = Intent(startingActivity, ChatActivity::class.java)
            intent.putExtra(ARG_REVEAL_START_LOCATION, startingLocation)
            startingActivity.startActivity(intent)
        }
    }
}