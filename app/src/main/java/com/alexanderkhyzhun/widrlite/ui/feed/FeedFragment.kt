package com.alexanderkhyzhun.widrlite.ui.feed

import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseFragment

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class FeedFragment : BaseFragment(R.layout.fragment_feed) {

    companion object {
        const val TAG = "FeedFragment"
        const val PAGER_POSITION = 0
        fun newInstance() = FeedFragment()
    }

}