package com.alexanderkhyzhun.widrlite.ui.adapters.delegates

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexanderkhyzhun.widrlite.data.models.ConversationItem
import com.alexanderkhyzhun.widrlite.data.models.NewsItem
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.trello.rxlifecycle2.LifecycleTransformer
import com.alexanderkhyzhun.widrlite.ui.adapters.DisplayableItem
import com.alexanderkhyzhun.widrlite.ui.adapters.viewholders.MessageViewHolder
import com.alexanderkhyzhun.widrlite.ui.adapters.viewholders.NewsViewHolder

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class NewsDelegateAdapter(
    private val click: (DisplayableItem) -> Unit,
    private val dispose: () -> LifecycleTransformer<Any>
) : AdapterDelegate<List<DisplayableItem>>() {

    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean =
        items[position] is NewsItem

    override fun onBindViewHolder(
        items: List<DisplayableItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as NewsViewHolder).bind(items[position], position, click, dispose)
    }

    override fun onCreateViewHolder(parent: ViewGroup) = NewsViewHolder.create(parent)
}