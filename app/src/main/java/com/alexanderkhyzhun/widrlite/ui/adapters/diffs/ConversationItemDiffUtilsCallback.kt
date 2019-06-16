package com.alexanderkhyzhun.widrlite.ui.adapters.diffs

import androidx.recyclerview.widget.DiffUtil
import com.alexanderkhyzhun.widrlite.data.models.ConversationItem
import com.alexanderkhyzhun.widrlite.ui.adapters.DisplayableItem
import com.alexanderkhyzhun.widrlite.ui.adapters.models.Message

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class ConversationItemDiffUtilsCallback(
    private val oldItems: List<DisplayableItem>,
    private val newItems: List<DisplayableItem>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = true

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        ((oldItems[oldItemPosition] as ConversationItem).body == (newItems[newItemPosition] as ConversationItem).body) &&
                ((oldItems[oldItemPosition] as ConversationItem).senderName == (newItems[newItemPosition] as ConversationItem).senderName)
}