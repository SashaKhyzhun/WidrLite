package com.alexanderkhyzhun.widrlite.ui.adapters.viewholders

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.data.models.MessageItem
import com.alexanderkhyzhun.widrlite.ui.adapters.DisplayableItem
import com.alexanderkhyzhun.widrlite.utils.setVisible
import com.alexanderkhyzhun.widrlite.utils.threeDots
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding2.view.clicks
import com.trello.rxlifecycle2.LifecycleTransformer
import kotlinx.android.synthetic.main.item_bubble_messages.view.*
import kotlinx.android.synthetic.main.item_location.view.*
import kotlinx.android.synthetic.main.item_message.view.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import timber.log.Timber

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

                item_message_tv_name.text = item.senderName
                item_message_tv_body.text = item.body.threeDots(40)
                item_message_tv_job.text = item.senderJob
                item_location_tv_city.text = item.senderLocation

                if (item.isNew) {
                    item_message_layout_messages.setVisible()
                    item_bubble_messages_tv_amount.text = item.amount
                    item_message_tv_body.typeface = Typeface.DEFAULT_BOLD
                }

                glideManager
                    .load(item.senderPhoto)
                    .apply(RequestOptions().circleCrop())
                    .into(item_message_iv_photo)

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