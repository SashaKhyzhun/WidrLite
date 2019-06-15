package com.alexanderkhyzhun.widrlite.ui.mvp

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.enums.Event
import com.alexanderkhyzhun.widrlite.ui.mvp.moxy.MvpAppCompatFragment
import com.jakewharton.rxbinding2.view.clicks
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.RxLifecycle
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit


open class BaseFragment(@LayoutRes private val layoutResId: Int) : MvpAppCompatFragment() {

    private val subject: BehaviorSubject<Event> = BehaviorSubject.createDefault(Event.CREATE)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutResId, container, false)


    override fun onDestroy() {
        super.onDestroy()
        subject.onNext(Event.DESTROY)
    }

    fun <T> bindUntilDestroy(): LifecycleTransformer<T> {
        return RxLifecycle.bindUntilEvent(subject, Event.DESTROY)
    }

    fun showSnack(msg: String?) {
        msg?.let { (activity as? BaseActivity)?.showSnack(it) }
    }

    @SuppressLint("CheckResult")
    fun Context.createAlertDialog(
        schedulers: Schedulers,
        title: String? = null,
        message: String? = null,
        buttonPos: String,
        buttonNeg: String,
        actionNeg: () -> Unit,
        actionPos: () -> Unit
    ): Dialog {
        val d = Dialog(this)
        d.requestWindowFeature(Window.FEATURE_NO_TITLE)
        d.setContentView(R.layout.dialog_default)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            d.window.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            d.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        d.findViewById<TextView>(R.id.dialog_title).text = title
        d.findViewById<TextView>(R.id.dialog_description).text = message
        d.findViewById<Button>(R.id.dialog_btn_positive).text = buttonPos
        d.findViewById<Button>(R.id.dialog_btn_negative).text = buttonNeg

        d.findViewById<Button>(R.id.dialog_btn_positive).clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe {
                actionPos()
                d.dismiss()
            }

        d.findViewById<Button>(R.id.dialog_btn_negative).clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe {
                actionNeg()
                d.dismiss()
            }

        d.setCancelable(false)
        return d
    }


}
