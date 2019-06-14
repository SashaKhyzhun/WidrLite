package com.alexanderkhyzhun.widrlite.data

import io.reactivex.Scheduler

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
interface Schedulers {

    fun single(): Scheduler

    fun io(): Scheduler

    fun computation(): Scheduler

    fun mainThread(): Scheduler
}