package com.alexanderkhyzhun.widrlite.ui.messages

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.ui.adapters.DelegateAdapter
import com.alexanderkhyzhun.widrlite.ui.adapters.DisplayableItem
import com.alexanderkhyzhun.widrlite.ui.adapters.delegates.MessageDelegateAdapter
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_messages.*
import org.koin.android.ext.android.inject

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class MessagesFragment : BaseFragment(R.layout.fragment_messages) {

    val schedulers: Schedulers by inject()

    private val delegateAdapter by lazy {
        DelegateAdapter<DisplayableItem>()
    }

    @InjectPresenter
    lateinit var presenter: MessagesPresenter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        delegateAdapter.delegatesManager.apply {
            addDelegate(
                MessageDelegateAdapter(
                    presenter::onNotificationClick,
                    ::bindUntilDestroy
                )
            )
        }

        with (fragment_messages_rv) {
            adapter = delegateAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        }
    }


    companion object {
        const val TAG = "MessagesFragment"
        const val PAGER_POSITION = 1
        fun newInstance() = MessagesFragment()
    }

}