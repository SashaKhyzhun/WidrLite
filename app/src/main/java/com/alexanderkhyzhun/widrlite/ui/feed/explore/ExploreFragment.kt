package com.alexanderkhyzhun.widrlite.ui.feed.explore

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.arellomobile.mvp.presenter.InjectPresenter
import org.koin.android.ext.android.inject

/**
 * @author Alexander Khyzhun
 * Created on 15 June, 2019
 */
class ExploreFragment : Fragment(R.layout.fragment_explore), ExploreView {


    val schedulers: Schedulers by inject()

    @InjectPresenter
    lateinit var presenter: ExplorePresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun renderView() {

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
        const val TAG = "ExploreFragment"
        const val LABEL = "Explorer les offres"
        const val PAGER_POSITION = 2
        fun newInstance() = ExploreFragment()
    }
}