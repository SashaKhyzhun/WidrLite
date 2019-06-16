package com.alexanderkhyzhun.widrlite.ui

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.MenuItem
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.data.models.NewsItem
import com.alexanderkhyzhun.widrlite.ui.adapters.MainPagerAdapter
import com.alexanderkhyzhun.widrlite.ui.chat.ChatActivity
import com.alexanderkhyzhun.widrlite.ui.chat2.ChatActivtiy2
import com.alexanderkhyzhun.widrlite.ui.conversations.ConversationsFragment
import com.alexanderkhyzhun.widrlite.ui.feed.FeedFragment
import com.alexanderkhyzhun.widrlite.ui.feed.news.NewsFragment
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseActivity
import com.alexanderkhyzhun.widrlite.ui.notifications.NotificationsFragment
import com.alexanderkhyzhun.widrlite.ui.profile.ProfileFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.navigation.NavigationView
import com.jakewharton.rxbinding2.view.clicks
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_slide_up_panel.*
import kotlinx.android.synthetic.main.item_slide_up_panel_header.*
import org.jetbrains.anko.share
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity(),
    MainView,
    ViewPager.OnPageChangeListener,
    BottomNavigationView.OnNavigationItemSelectedListener,
    ProfileFragment.Callback,
    NotificationsFragment.Callback,
    NewsFragment.Callback {

    val schedulers: Schedulers by inject()
    val glideManager: RequestManager by inject()

    @InjectPresenter
    lateinit var presenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpBottomNavigation()
        setUpViewPager()
        setUpNavigationDrawer()
    }

    override fun updatePagerStatus(enabled: Boolean) {
        activity_main_pager.setPagingEnabled(enabled)
    }

    private fun setUpViewPager() {
        activity_main_pager.offscreenPageLimit = OFFSCREEN_PAGE_LIMIT
        activity_main_pager.adapter = MainPagerAdapter(supportFragmentManager)
        activity_main_pager.addOnPageChangeListener(this)
    }

    private fun setUpBottomNavigation() {
        activity_main_navigation.setOnNavigationItemSelectedListener(this)
        activity_main_navigation.selectedItemId = R.id.navigation_feed
        activity_main_navigation.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
    }

    private fun setUpNavigationDrawer() {
        val navigationView: NavigationView = findViewById(R.id.nav_view)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            drawer_layout.closeDrawers()
            //drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            when (menuItem.itemId) {
                R.id.nav_profile -> toast("Profile")
                R.id.nav_settings -> toast("Settings")
                R.id.nav_terms -> toast("Terms")
            }
            true
        }
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        when (position) {
            FeedFragment.PAGER_POSITION -> {
                activity_main_navigation.selectedItemId = R.id.navigation_feed
            }
            ConversationsFragment.PAGER_POSITION -> {
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
            R.id.navigation_messages -> {
                activity_main_pager.currentItem = ConversationsFragment.PAGER_POSITION
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

    @SuppressLint("CheckResult")
    override fun showSlideUp(newsItem: NewsItem) {
        Timber.d("show slide up = $newsItem")

        item_slide_up_tv_description.text = newsItem.postDescription
        item_slide_up_tv_date.text = newsItem.postDate
        item_slide_up_author_name.text = newsItem.authorName

        glideManager
            .load(newsItem.authorImage)
            .apply(RequestOptions().circleCrop())
            .load(item_slide_up_author_photo)


        item_slide_up_layout_facebook.clicks()
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe {
                presenter.onPanelClickFacebook(newsItem)
            }

        item_slide_up_layout_share.clicks()
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe {
                presenter.onPanelClickShare(newsItem)
            }

        item_slide_up_button_send.clicks()
            .debounce(CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe {
                presenter.onPanelClickSendRecommendation(newsItem)
            }



        fragment_services_parent_sliding_up_panel.panelState =
            SlidingUpPanelLayout.PanelState.EXPANDED
    }


    override fun onPanelClose() {
        fragment_services_parent_sliding_up_panel.panelState =
            SlidingUpPanelLayout.PanelState.COLLAPSED
    }

    override fun openConversation() {
        Handler().postDelayed({
            startActivity(Intent(this, ChatActivtiy2::class.java))
        }, CLICK_DEBOUNCE)
    }

    override fun onPanelClickedFacebook() {
        toast("Facebook")
    }

    override fun onPanelClickedSendRecommendation() {
        toast("Recommendation")
    }

    override fun onPanelClickedShare(postTitle: String, postDescription: String) {
        share(postTitle, postDescription)
    }

    override fun onClickedBurger() {
        //drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED) // check it.
        drawer_layout.openDrawer(GravityCompat.END)
    }

    override fun takePhoto() {

    }

    override fun importPhoto() {
        if (checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            requestPermissions(arrayOf(WRITE_EXTERNAL_STORAGE), MY_STORAGE_PERMISSION_CODE)
        } else {
            startActivityForResult(
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI),
                STORAGE_REQUEST
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CAMERA_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {
                    val photo = data?.extras?.get("data") as Bitmap
                    presenter.savePhotoFromCamera(photo)
                }
            }
            STORAGE_REQUEST -> {

                // todo: find and fix time delay.
                if (resultCode == Activity.RESULT_OK) {
                    val selectedImage = data?.data
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                    val cursor = contentResolver.query(
                        selectedImage, filePathColumn,
                        null,
                        null,
                        null
                    )
                    cursor!!.moveToFirst()
                    val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                    val picturePath = cursor.getString(columnIndex)
                    val photo = BitmapFactory.decodeFile(picturePath)
                    cursor.close()


                    presenter.savePhotoFromStorage(photo)
                }
            }
        }
    }


    override fun renderError(throwable: Throwable) {

    }

    override fun renderMessage(text: String) {

    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    companion object {
        private const val OFFSCREEN_PAGE_LIMIT = 4
        const val TAG = "MainActivity"

        private const val CAMERA_REQUEST = 1888
        private const val STORAGE_REQUEST = 1889
        private const val MY_CAMERA_PERMISSION_CODE = 100
        private const val MY_STORAGE_PERMISSION_CODE = 101

        fun getIntent(context: Context?) = Intent(context, MainActivity::class.java)
    }
}
