package com.alexanderkhyzhun.widrlite.ui.adapters.diffs

import androidx.recyclerview.widget.DiffUtil
import com.alexanderkhyzhun.widrlite.data.models.NewsItem
import com.alexanderkhyzhun.widrlite.data.models.NotificationItem
import com.alexanderkhyzhun.widrlite.ui.adapters.DisplayableItem

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class NewsItemDiffUtilsCallback(
    private val oldItems: List<DisplayableItem>,
    private val newItems: List<DisplayableItem>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = true

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        ((oldItems[oldItemPosition] as NewsItem).postId
            == (newItems[newItemPosition] as NewsItem).postId) &&
                ((oldItems[oldItemPosition] as NewsItem).postDescription
                    == (newItems[newItemPosition] as NewsItem).postDescription) &&
                        ((oldItems[oldItemPosition] as NewsItem).authorImage
                            == (newItems[newItemPosition] as NewsItem).authorImage) &&
                                ((oldItems[oldItemPosition] as NewsItem).authorName
                                    == (newItems[newItemPosition] as NewsItem).authorName)
}