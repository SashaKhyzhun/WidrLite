package com.alexanderkhyzhun.widrlite.data.impl

import com.alexanderkhyzhun.widrlite.data.Schedulers
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers


class SchedulersImpl : Schedulers {

    override fun single(): Scheduler {
        return io.reactivex.schedulers.Schedulers.single()
    }

    override fun io(): Scheduler {
        return io.reactivex.schedulers.Schedulers.io()
    }

    override fun computation(): Scheduler {
        return io.reactivex.schedulers.Schedulers.computation()
    }

    override fun mainThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

}