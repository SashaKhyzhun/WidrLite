package com.alexanderkhyzhun.widrlite.ui.adapters.delegates

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexanderkhyzhun.widrlite.data.models.ConversationItem
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.trello.rxlifecycle2.LifecycleTransformer
import com.alexanderkhyzhun.widrlite.ui.adapters.DisplayableItem
import com.alexanderkhyzhun.widrlite.ui.adapters.viewholders.MessageViewHolder

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class MessageDelegateAdapter(
    private val click: (DisplayableItem) -> Unit,
    private val dispose: () -> LifecycleTransformer<Any>
) : AdapterDelegate<List<DisplayableItem>>() {

    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean =
        items[position] is ConversationItem

    override fun onBindViewHolder(
        items: List<DisplayableItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as MessageViewHolder).bind(items[position], position, click, dispose)
    }

    override fun onCreateViewHolder(parent: ViewGroup) = MessageViewHolder.create(parent)
}