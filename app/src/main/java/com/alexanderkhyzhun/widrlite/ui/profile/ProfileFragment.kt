package com.alexanderkhyzhun.widrlite.ui.profile

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseActivity
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.item_burger_icon.*
import kotlinx.android.synthetic.main.item_profile_header.*
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
@SuppressLint("CheckResult")
class ProfileFragment : BaseFragment(R.layout.fragment_profile), ProfileView {

    interface Callback {
        fun takePhoto()
        fun importPhoto()
        fun onClickedBurger()
    }

    val schedulers: Schedulers by inject()
    val glideManager: RequestManager by inject()

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    private var callback: Callback? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Callback) {
            callback = context
        } else {
            throw RuntimeException("$context must implement Callback")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        item_profile_proposal.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { presenter.onClickProposal() }

        fragment_profile_iv_user_image.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { presenter.onClickTakePhoto() }

        fragment_profile_layout_burger.clicks()
            //.debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { presenter.onClickBurger() }

    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    override fun renderName(userName: String) {
        fragment_profile_tv_name.text = userName
    }

    override fun renderImage(image: Bitmap) {
        glideManager
            .load(image)
            .apply(RequestOptions().circleCrop())
            .into(fragment_profile_iv_user_image)
    }

    override fun renderImage(image: String) {
        glideManager
            .load(image)
            .apply(RequestOptions().circleCrop())
            .into(fragment_profile_iv_user_image)
    }

    override fun onClickedTakePhoto() {
        callback?.takePhoto()
    }

    override fun onClickedImport() {
        callback?.importPhoto()
    }

    override fun onClickedSave() {

    }

    override fun onClickedProposal() {
        toast("Proposal")
    }

    override fun onClickedBurger() {
        toast("Burger")
        callback?.onClickedBurger()
    }

    override fun savePhotoFromCamera(photo: Bitmap) {
        presenter.savePhoto(photo)
    }

    override fun savePhotoFromStorage(photo: Bitmap) {
        presenter.savePhoto(photo)
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
        const val TAG = "ProfileFragment"
        const val PAGER_POSITION = 3


        fun newInstance() = ProfileFragment()
    }

}