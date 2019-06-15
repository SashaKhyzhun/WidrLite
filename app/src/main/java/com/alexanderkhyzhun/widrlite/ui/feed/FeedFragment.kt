package com.alexanderkhyzhun.widrlite.ui.feed

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.ui.adapters.FeedPagerAdapter
import com.alexanderkhyzhun.widrlite.ui.feed.news.NewsFragment
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_feed.*
import org.koin.android.ext.android.inject

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
class FeedFragment : BaseFragment(R.layout.fragment_feed),
    FeedView,
    ViewPager.OnPageChangeListener,
    TabLayout.OnTabSelectedListener {

    val schedulers: Schedulers by inject()

    @InjectPresenter
    lateinit var presenter: FeedPresenter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_feed_view_pager.offscreenPageLimit = OFFSCREEN_PAGE_LIMIT
        fragment_feed_view_pager.adapter = FeedPagerAdapter(childFragmentManager)
        fragment_feed_view_pager.addOnPageChangeListener(this)

        fragment_feed_tab_layout.setupWithViewPager(fragment_feed_view_pager)
        fragment_feed_tab_layout.addOnTabSelectedListener(this)
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onTabReselected(tab: TabLayout.Tab?) {
        fragment_feed_view_pager.currentItem = tab?.position ?: 0
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {}

    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    override fun onTabSelected(tab: TabLayout.Tab?) {}

    override fun renderView() {

    }

    override fun showLoader() {
        /* code implementation */
    }

    override fun hideLoader() {
        /* code implementation */
    }

    override fun renderMessage(text: String) {
        showSnack(text)
    }

    override fun renderError(throwable: Throwable) {
        showSnack(throwable.message)
    }

    companion object {
        const val TAG = "FeedFragment"
        const val PAGER_POSITION = 0
        const val OFFSCREEN_PAGE_LIMIT = 3
        fun newInstance() = FeedFragment()
    }

}