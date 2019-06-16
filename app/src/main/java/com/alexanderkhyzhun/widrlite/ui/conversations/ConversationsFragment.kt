package com.alexanderkhyzhun.widrlite.ui.conversations

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.data.models.ConversationItem
import com.alexanderkhyzhun.widrlite.ui.adapters.DelegateAdapter
import com.alexanderkhyzhun.widrlite.ui.adapters.DisplayableItem
import com.alexanderkhyzhun.widrlite.ui.adapters.decoratos.LinearDecorator
import com.alexanderkhyzhun.widrlite.ui.adapters.delegates.MessageDelegateAdapter
import com.alexanderkhyzhun.widrlite.ui.adapters.diffs.NotificationItemDiffUtilsCallback
import com.alexanderkhyzhun.widrlite.ui.chat.ChatActivity
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseActivity
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseFragment
import com.alexanderkhyzhun.widrlite.utils.dp
import com.arellomobile.mvp.presenter.InjectPresenter
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.fragment_messages.*
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class ConversationsFragment : BaseFragment(R.layout.fragment_messages), ConversationsView {

    val schedulers: Schedulers by inject()

    private val delegateAdapter by lazy {
        DelegateAdapter<DisplayableItem>()
    }

    @InjectPresenter
    lateinit var presenter: ConversationsPresenter


    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        delegateAdapter.delegatesManager.apply {
            addDelegate(
                MessageDelegateAdapter(
                    presenter::onMessageClick,
                    ::bindUntilDestroy
                )
            )
        }

        with(fragment_messages_rv) {
            adapter = delegateAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
            addItemDecoration(
                LinearDecorator(
                    8.dp(),
                    8.dp(),
                    8.dp(),
                    0.dp()
                )
            )
        }

        fragment_messages_iv_search.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { toast("Search") }
    }

    override fun renderConversations(data: List<ConversationItem>) {
        delegateAdapter.set(data) { old, new ->
            NotificationItemDiffUtilsCallback(
                old,
                new
            )
        }
    }

    override fun onClickedOpenChat() {
        startActivity(ChatActivity.getIntent(context))
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
        const val TAG = "ConversationsFragment"
        const val PAGER_POSITION = 1
        fun newInstance() = ConversationsFragment()
    }

}