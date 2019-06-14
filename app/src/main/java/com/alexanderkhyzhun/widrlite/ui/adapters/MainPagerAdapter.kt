package com.alexanderkhyzhun.widrlite.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.alexanderkhyzhun.widrlite.ui.feed.FeedFragment
import com.alexanderkhyzhun.widrlite.ui.conversations.ConversationsFragment
import com.alexanderkhyzhun.widrlite.ui.notifications.NotificationsFragment
import com.alexanderkhyzhun.widrlite.ui.profile.ProfileFragment

class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragments = listOf<Fragment>(
        FeedFragment(),
        ConversationsFragment(),
        NotificationsFragment(),
        ProfileFragment()
    )

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

}