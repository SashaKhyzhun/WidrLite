package com.alexanderkhyzhun.widrlite.ui.adapters.viewholders

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.data.models.ConversationItem
import com.alexanderkhyzhun.widrlite.data.models.NewsItem
import com.alexanderkhyzhun.widrlite.ui.adapters.DisplayableItem
import com.alexanderkhyzhun.widrlite.utils.setVisible
import com.alexanderkhyzhun.widrlite.utils.threeDots
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding2.view.clicks
import com.trello.rxlifecycle2.LifecycleTransformer
import kotlinx.android.synthetic.main.item_bubble_messages.view.*
import kotlinx.android.synthetic.main.item_location.view.*
import kotlinx.android.synthetic.main.item_conversation.view.*
import kotlinx.android.synthetic.main.item_news.view.*
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
        dispose: () -> LifecycleTransformer<Any>
    ) {
        (item as? NewsItem)?.let {
            with(itemView) {
                this.clicks()
                    .compose(dispose.invoke())
                    .observeOn(schedulers.mainThread())
                    .subscribe { click.invoke(item) }

                item_news_author_name.text = item.authorName
                item_news_tv_description.text = item.postDescription.threeDots(100)
                item_news_tv_date.text = item.postDate

                item_news_iv_bg.setBackgroundColor(Color.parseColor(item.bgColor))

                glideManager
                    .load(item.authorImage)
                    .apply(RequestOptions().circleCrop())
                    .into(item_news_author_photo)

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