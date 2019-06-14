package com.alexanderkhyzhun.widrlite.ui.profile

import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseFragment

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class ProfileFragment : BaseFragment(R.layout.fragment_profile) {



    companion object {
        const val TAG = "ProfileFragment"
        const val PAGER_POSITION = 3
        fun newInstance() = ProfileFragment()
    }

}