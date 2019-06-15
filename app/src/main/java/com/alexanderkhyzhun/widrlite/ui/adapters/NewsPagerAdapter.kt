package com.alexanderkhyzhun.widrlite.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.alexanderkhyzhun.widrlite.ui.feed.news.ContentFragment

class NewsPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val fragments = listOf<Fragment>(
        ContentFragment(),
        ContentFragment(),
        ContentFragment()
    )

    private val labels = listOf(
        ContentFragment.LABEL,
        ContentFragment.LABEL,
        ContentFragment.LABEL
    )

    override fun getItem(position: Int) = fragments[position]

    override fun getPageTitle(position: Int) = labels[position]

    override fun getCount() = fragments.size

}