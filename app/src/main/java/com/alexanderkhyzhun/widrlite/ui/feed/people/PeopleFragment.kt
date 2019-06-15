package com.alexanderkhyzhun.widrlite.ui.feed.people

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
class PeopleFragment : Fragment(R.layout.fragment_people), PeopleView {


    val schedulers: Schedulers by inject()

    @InjectPresenter
    lateinit var presenter: PeoplePresenter

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
        const val TAG = "PeopleFragment"
        const val LABEL = "Trouver on profile"
        const val PAGER_POSITION = 3
        fun newInstance() = PeopleFragment()
    }
}