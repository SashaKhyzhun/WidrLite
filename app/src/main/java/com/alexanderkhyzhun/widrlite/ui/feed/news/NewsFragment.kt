package com.alexanderkhyzhun.widrlite.ui.feed.news

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
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
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseActivity
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseFragment
import com.alexanderkhyzhun.widrlite.utils.dp
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding2.view.clicks
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.item_news_post_header.*
import kotlinx.android.synthetic.main.item_slide_up_panel.*
import org.jetbrains.anko.support.v4.share
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

/**
 * @author Alexander Khyzhun
 * Created on 15 June, 2019
 */
class NewsFragment : BaseFragment(R.layout.fragment_news), NewsView {

    interface Callback {
        fun showSlideUp(newsItem: NewsItem)
    }

    val schedulers: Schedulers by inject()


    private val delegateAdapter by lazy { DelegateAdapter<DisplayableItem>() }
    private var callback: Callback? = null


    @InjectPresenter
    lateinit var presenter: NewsPresenter



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
                NewsDelegateAdapter(
                    presenter::onPostClick,
                    presenter::onMutualClick,
                    presenter::onCommentClick,
                    presenter::onShareClick,
                    presenter::onOfferClick,
                    ::bindUntilDestroy
                )
            )
        }

        with(fragment_news_list) {
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

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    override fun renderView(data: List<NewsItem>) {
        delegateAdapter.set(data) { old, new ->
            NewsItemDiffUtilsCallback(
                old,
                new
            )
        }
    }

    override fun onClickedShowMutual() {
        toast("show mutual")
    }

    override fun onClickedComment() {
        toast("comment")
    }

    override fun onClickedShare(postTitle: String, postDescription: String) {
        share(postTitle, postDescription)
    }

    @SuppressLint("CheckResult")
    override fun onClickedOffer(newsItem: NewsItem) {
        callback?.showSlideUp(newsItem)
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
        const val TAG = "NewsFragment"
        const val LABEL = "Toutes les annonces"
        const val PAGER_POSITION = 1
        fun newInstance() = NewsFragment()
    }
}