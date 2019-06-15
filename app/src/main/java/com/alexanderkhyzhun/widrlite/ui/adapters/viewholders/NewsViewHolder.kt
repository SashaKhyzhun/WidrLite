package com.alexanderkhyzhun.widrlite.ui.adapters.viewholders

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.data.models.NewsItem
import com.alexanderkhyzhun.widrlite.ui.adapters.DisplayableItem
import com.alexanderkhyzhun.widrlite.utils.threeDots
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding2.view.clicks
import com.trello.rxlifecycle2.LifecycleTransformer
import kotlinx.android.synthetic.main.item_news.view.*
import kotlinx.android.synthetic.main.item_news_post_body.view.*
import kotlinx.android.synthetic.main.item_news_post_header.view.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view), KoinComponent {

    val glideManager: RequestManager by inject()
    val schedulers: Schedulers by inject()

    fun bind(
        item: DisplayableItem?,
        position: Int,
        click: (DisplayableItem) -> Unit,
        mutualClick: (DisplayableItem) -> Unit,
        commentClick: (DisplayableItem) -> Unit,
        shareClick: (DisplayableItem) -> Unit,
        offerClick: (DisplayableItem) -> Unit,
        dispose: () -> LifecycleTransformer<Any>
    ) {
        (item as? NewsItem)?.let {
            with(itemView) {

                item_news_author_name.text = item.authorName
                item_news_tv_description.text = item.postDescription.threeDots(100)
                item_news_tv_date.text = item.postDate
                item_news_post_tv_title.text = item.postTitle
                item_news_post_tv_location.text = item.postLocation
                item_news_post_layout_parent.setBackgroundColor(Color.parseColor(item.bgColor))


                glideManager
                    .load(item.authorImage)
                    .apply(RequestOptions().circleCrop())
                    .into(item_news_author_photo)

                this.clicks()
                    .compose(dispose.invoke())
                    .observeOn(schedulers.mainThread())
                    .subscribe { click.invoke(item) }

                item_news_layout_mutual.clicks()
                    .compose(dispose.invoke())
                    .observeOn(schedulers.mainThread())
                    .subscribe { mutualClick.invoke(item) }

                item_news_iv_comment.clicks()
                    .compose(dispose.invoke())
                    .observeOn(schedulers.mainThread())
                    .subscribe { commentClick.invoke(item) }

                item_news_iv_share.clicks()
                    .compose(dispose.invoke())
                    .observeOn(schedulers.mainThread())
                    .subscribe { shareClick.invoke(item) }

                item_news_post_layout_parent.clicks()
                    .compose(dispose.invoke())
                    .observeOn(schedulers.mainThread())
                    .subscribe { offerClick.invoke(item) }


            }
        }
    }

    companion object {
        fun create(parent: ViewGroup) = NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_news,
                parent,
                false
            )
        )
    }

}