package com.alexanderkhyzhun.widrlite.ui.adapters.diffs

import androidx.recyclerview.widget.DiffUtil
import com.alexanderkhyzhun.widrlite.ui.adapters.DisplayableItem
import com.alexanderkhyzhun.widrlite.ui.adapters.models.Message

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class MessageItemDiffUtilsCallback(
    private val oldItems: List<DisplayableItem>,
    private val newItems: List<DisplayableItem>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = true

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        ((oldItems[oldItemPosition] as Message).text == (newItems[newItemPosition] as Message).text) &&
                ((oldItems[oldItemPosition] as Message).memberData.name == (newItems[newItemPosition] as Message).memberData.name)
}