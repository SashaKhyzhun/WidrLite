package com.alexanderkhyzhun.widrlite.ui.adapters.diffs

import androidx.recyclerview.widget.DiffUtil
import com.alexanderkhyzhun.widrlite.data.models.NotificationItem
import com.alexanderkhyzhun.widrlite.ui.adapters.DisplayableItem

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class NotificationItemDiffUtilsCallback(
    private val oldItems: List<DisplayableItem>,
    private val newItems: List<DisplayableItem>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = true

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        ((oldItems[oldItemPosition] as NotificationItem).id == (newItems[newItemPosition] as NotificationItem).id) &&
                ((oldItems[oldItemPosition] as NotificationItem).isNew == (newItems[newItemPosition] as NotificationItem).isNew)
}