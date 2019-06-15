package com.alexanderkhyzhun.widrlite.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.alexanderkhyzhun.widrlite.ui.feed.FeedFragment
import com.alexanderkhyzhun.widrlite.ui.conversations.ConversationsFragment
import com.alexanderkhyzhun.widrlite.ui.feed.explore.ExploreFragment
import com.alexanderkhyzhun.widrlite.ui.feed.news.NewsFragment
import com.alexanderkhyzhun.widrlite.ui.feed.people.PeopleFragment
import com.alexanderkhyzhun.widrlite.ui.notifications.NotificationsFragment
import com.alexanderkhyzhun.widrlite.ui.profile.ProfileFragment

class FeedPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val fragments = listOf<Fragment>(
        NewsFragment(),
        ExploreFragment(),
        ExploreFragment()
    )

    private val labels = listOf(
        NewsFragment.LABEL,
        ExploreFragment.LABEL,
        PeopleFragment.LABEL
    )

    override fun getItem(position: Int) = fragments[position]

    override fun getPageTitle(position: Int) = labels[position]

    override fun getCount() = fragments.size

}