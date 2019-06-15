package com.alexanderkhyzhun.widrlite.ui.feed.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.data.models.NewsItem
import com.alexanderkhyzhun.widrlite.ui.adapters.DelegateAdapter
import com.alexanderkhyzhun.widrlite.ui.adapters.DisplayableItem
import com.alexanderkhyzhun.widrlite.ui.adapters.decoratos.LinearDecorator
import com.alexanderkhyzhun.widrlite.ui.adapters.delegates.NewsDelegateAdapter
import com.alexanderkhyzhun.widrlite.ui.adapters.diffs.NewsItemDiffUtilsCallback
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseFragment
import com.alexanderkhyzhun.widrlite.utils.dp
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_news.*
import org.koin.android.ext.android.inject

/**
 * @author Alexander Khyzhun
 * Created on 15 June, 2019
 */
class NewsFragment : BaseFragment(R.layout.fragment_news), NewsView {

    val schedulers: Schedulers by inject()

    private val delegateAdapter by lazy {
        DelegateAdapter<DisplayableItem>()
    }


    @InjectPresenter
    lateinit var presenter: NewsPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        delegateAdapter.delegatesManager.apply {
            addDelegate(
                NewsDelegateAdapter(
                    presenter::onMessageClick,
                    ::bindUntilDestroy
                )
            )
        }

        with (fragment_news_list) {
            adapter = delegateAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
            addItemDecoration(
                LinearDecorator(
                    0.dp(),
                    0.dp(),
                    8.dp(),
                    0.dp()
                )
            )
        }
    }

    override fun renderView(data: List<NewsItem>) {
        delegateAdapter.set(data) { old, new ->
            NewsItemDiffUtilsCallback(
                old,
                new
            )
        }
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
        const val TAG = "NewsFragment"
        const val LABEL = "Toutes les annonces"
        const val PAGER_POSITION = 1
        fun newInstance() = NewsFragment()
    }
}