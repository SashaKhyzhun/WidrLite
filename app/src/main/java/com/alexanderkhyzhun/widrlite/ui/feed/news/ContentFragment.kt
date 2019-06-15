package com.alexanderkhyzhun.widrlite.ui.feed.news

import android.os.Bundle
import android.view.View
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseFragment

/**
 * @author Alexander Khyzhun
 * Created on 15 June, 2019
 */
class ContentFragment : BaseFragment(R.layout.fragment_content) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    companion object {
        const val TAG = "ContentFragment"
        const val LABEL = "Content"
        const val PAGER_POSITION = 0
        const val OFFSCREEN_PAGE_LIMIT = 3
        fun newInstance() = ContentFragment()
    }

}