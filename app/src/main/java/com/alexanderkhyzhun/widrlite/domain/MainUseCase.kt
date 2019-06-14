package com.alexanderkhyzhun.widrlite.domain

import android.graphics.Bitmap

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
interface MainUseCase {

    fun storeUserPhoto(bitmap: Bitmap)

}