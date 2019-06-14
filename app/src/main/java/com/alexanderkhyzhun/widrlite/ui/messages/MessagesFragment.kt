package com.alexanderkhyzhun.widrlite.ui.messages

import android.os.Bundle
import android.view.View
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseFragment

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class MessagesFragment : BaseFragment(R.layout.fragment_messages) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    companion object {
        const val TAG = "MessagesFragment"
        const val PAGER_POSITION = 1
        fun newInstance() = MessagesFragment()
    }

}