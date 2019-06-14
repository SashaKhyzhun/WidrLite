package com.alexanderkhyzhun.widrlite.ui.notifications

import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_notifications.*
import org.koin.android.ext.android.inject

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class NotificationsFragment : BaseFragment(R.layout.fragment_notifications), NotificationView {

    val schedulers: Schedulers by inject()

    private val notifDelegateAdapter by lazy {
        DelegateAdapter<DisplayableItem>()
    }

    @InjectPresenter
    lateinit var presenter: NotificationPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notifDelegateAdapter.delegatesManager.apply {
            addDelegate(
                NotificationDelegateAdapter(
                    presenter::onNotificationClick,
                    ::bindUntilDestroy
                )
            )
        }

        with (fragment_notif_rv) {
            adapter = notifDelegateAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    override fun renderNotifications(data: List<NotificationItem>) {
        notifDelegateAdapter.set(data) { old, new ->
            NotificationItemDiffUtilsCallback(
                old,
                new
            )
        }
    }

    override fun renderView() {

    }

    override fun renderError(throwable: Throwable) {

    }

    override fun renderMessage(text: String) {

    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    companion object {
        const val TAG = "NotificationsFragment"
        const val PAGER_POSITION = 2
        fun newInstance() = NotificationsFragment()
    }

}