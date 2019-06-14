package com.alexanderkhyzhun.widrlite.ui.adapters.viewholders

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.data.models.MessageItem
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
class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view), KoinComponent {

    val glideManager: RequestManager by inject()
    val schedulers: Schedulers by inject()

    fun bind(
        item: DisplayableItem?,
        position: Int,
        click: (DisplayableItem) -> Unit,
        dispose: () -> LifecycleTransformer<Any>
    ) {
        (item as? MessageItem)?.let {
            with(itemView) {
                this.clicks()
                    .compose(dispose.invoke())
                    .observeOn(schedulers.mainThread())
                    .subscribe { click.invoke(item) }


                if (item.isNew) {
                    item_message
                }

//                glideManager
//                    .load(item.image)
//                    .apply(RequestOptions().circleCrop())
//                    .into(item_notif_image_notifications)

            }
        }
    }

    companion object {
        fun create(parent: ViewGroup) = MessageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_message,
                parent,
                false
            )
        )
    }

}