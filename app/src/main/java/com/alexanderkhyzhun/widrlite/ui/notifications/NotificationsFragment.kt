package com.alexanderkhyzhun.widrlite.ui.notifications

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.data.models.NotificationItem
import com.alexanderkhyzhun.widrlite.ui.adapters.DelegateAdapter
import com.alexanderkhyzhun.widrlite.ui.adapters.DisplayableItem
import com.alexanderkhyzhun.widrlite.ui.adapters.delegates.NotificationDelegateAdapter
import com.alexanderkhyzhun.widrlite.ui.adapters.diffs.NotificationItemDiffUtilsCallback
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener
import kotlinx.android.synthetic.main.fragment_notifications.*
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class NotificationsFragment : BaseFragment(R.layout.fragment_notifications), NotificationsView {

    interface Callback {
        fun updatePagerStatus(enabled: Boolean)
    }

    val schedulers: Schedulers by inject()

    private val delegateAdapter by lazy {
        DelegateAdapter<DisplayableItem>()
    }

    @InjectPresenter
    lateinit var presenter: NotificationsPresenter

    private var callback: Callback? = null

    private lateinit var onTouchIncomingListener: RecyclerTouchListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Callback) {
            callback = context
        } else {
            throw RuntimeException("$context must implement Callback")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        delegateAdapter.delegatesManager.apply {
            addDelegate(
                NotificationDelegateAdapter(
                    presenter::onNotificationClick,
                    ::bindUntilDestroy
                )
            )
        }

        with (fragment_notif_rv) {
            adapter = delegateAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            isNestedScrollingEnabled = true
        }


        fragment_notif_rv.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    callback?.updatePagerStatus(false)
                }
                MotionEvent.ACTION_MOVE-> {
                    callback?.updatePagerStatus(false)
                }
                MotionEvent.ACTION_UP -> {
                    callback?.updatePagerStatus(true)
                }
            }

            false
        }

        onTouchIncomingListener = RecyclerTouchListener(activity, fragment_notif_rv)
        onTouchIncomingListener
            .setSwipeOptionViews(R.id.item_notif_layout_read)
            .setSwipeable(R.id.rowFG, R.id.rowBG) { viewID, position ->
                when (viewID) {
                    R.id.item_notif_layout_read -> {
                        toast("Read!")
                    }
                }
            }
    }

    override fun onResume() {
        super.onResume()
        fragment_notif_rv.addOnItemTouchListener(onTouchIncomingListener)
    }

    override fun onPause() {
        super.onPause()
        fragment_notif_rv.removeOnItemTouchListener(onTouchIncomingListener)
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    override fun renderNotifications(data: List<NotificationItem>) {
        delegateAdapter.set(data) { old, new ->
            NotificationItemDiffUtilsCallback(
                old,
                new
            )
        }
    }

    override fun renderView() {

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
        const val TAG = "NotificationsFragment"
        const val PAGER_POSITION = 2
        fun newInstance() = NotificationsFragment()
    }

}