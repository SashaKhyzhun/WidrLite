package com.alexanderkhyzhun.widrlite.ui.notifications

import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseFragment

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class NotificationsFragment : BaseFragment(R.layout.fragment_notifications) {



    companion object {
        const val TAG = "NotificationsFragment"
        const val PAGER_POSITION = 2
        fun newInstance() = NotificationsFragment()
    }

}