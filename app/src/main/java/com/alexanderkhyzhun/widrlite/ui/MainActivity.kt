package com.alexanderkhyzhun.widrlite.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.ui.adapters.MainPagerAdapter
import com.alexanderkhyzhun.widrlite.ui.feed.FeedFragment
import com.alexanderkhyzhun.widrlite.ui.messages.MessagesFragment
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseActivity
import com.alexanderkhyzhun.widrlite.ui.notifications.NotificationsFragment
import com.alexanderkhyzhun.widrlite.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),
    ViewPager.OnPageChangeListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpBottomNavigation()
        setUpViewPager()
        setTranslucentStatusBar()
    }


    private fun setUpViewPager() {
        activity_main_pager.offscreenPageLimit = OFFSCREEN_PAGE_LIMIT
        activity_main_pager.adapter = MainPagerAdapter(supportFragmentManager)
        activity_main_navigation.selectedItemId = R.id.navigation_feed
        activity_main_pager.addOnPageChangeListener(this)
    }

    private fun setUpBottomNavigation() {
        activity_main_navigation.setOnNavigationItemSelectedListener(this)
        activity_main_navigation.selectedItemId = R.id.navigation_profile
        activity_main_navigation.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}


    override fun onPageSelected(position: Int) {
        when (position) {
            FeedFragment.PAGER_POSITION -> {
                activity_main_navigation.selectedItemId = R.id.navigation_feed
            }
            MessagesFragment.PAGER_POSITION -> {
                activity_main_navigation.selectedItemId = R.id.navigation_messages
            }
            NotificationsFragment.PAGER_POSITION -> {
                activity_main_navigation.selectedItemId = R.id.navigation_notifications
            }
            ProfileFragment.PAGER_POSITION -> {
                activity_main_navigation.selectedItemId = R.id.navigation_profile
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_feed -> {
                activity_main_pager.currentItem = FeedFragment.PAGER_POSITION
            }
            R.id.navigation_messages-> {
                activity_main_pager.currentItem = MessagesFragment.PAGER_POSITION
            }
            R.id.navigation_notifications -> {
                activity_main_pager.currentItem = NotificationsFragment.PAGER_POSITION
            }
            R.id.navigation_profile -> {
                activity_main_pager.currentItem = ProfileFragment.PAGER_POSITION
            }
        }
        return true
    }

    companion object {
        private const val OFFSCREEN_PAGE_LIMIT = 4
        const val TAG = "MainActivity"
        fun getIntent(context: Context?) = Intent(context, MainActivity::class.java)
    }
}
