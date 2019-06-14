package com.alexanderkhyzhun.widrlite.ui.adapters.viewholders

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.data.models.NotificationItem
import com.alexanderkhyzhun.widrlite.ui.adapters.DisplayableItem
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding2.view.clicks
import com.trello.rxlifecycle2.LifecycleTransformer
import kotlinx.android.synthetic.main.item_notifications.view.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class NotificationViewHolder(view: View) : RecyclerView.ViewHolder(view), KoinComponent {

    val glideManager: RequestManager by inject()

    val schedulers: Schedulers by inject()

    fun bind(
        item: DisplayableItem?,
        position: Int,
        click: (DisplayableItem) -> Unit,
        dispose: () -> LifecycleTransformer<Any>
    ) {
        (item as? NotificationItem)?.let {
            with(itemView) {
                this.clicks()
                    .compose(dispose.invoke())
                    .observeOn(schedulers.mainThread())
                    .subscribe { click.invoke(item) }

                /**
                 * Idea how to part different types of notifications:
                 * 1. find if notification text contains a user name
                 * 2. if so, cut it of
                 * 3. make it bold with html tags or SpannableStringBuilder
                 * 4. put it back.
                 *
                 * Or even backend can send me already formatted text ;)
                 */
                val fullMessage = "<b>${item.name}</b> ${item.message}"
                item_notif_description_notifications.text = Html.fromHtml(fullMessage)

                glideManager
                    .load(item.image)
                    .apply(RequestOptions().circleCrop())
                    .into(item_notif_image_notifications)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup) = NotificationViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_notifications,
                parent,
                false
            )
        )
    }

}