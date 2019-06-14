package com.alexanderkhyzhun.widrlite.ui

import com.alexanderkhyzhun.widrlite.ui.mvp.ErrorView
import com.alexanderkhyzhun.widrlite.ui.mvp.LoadingView
import com.arellomobile.mvp.MvpView

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */

interface MainView : MvpView, ErrorView, LoadingView {

}