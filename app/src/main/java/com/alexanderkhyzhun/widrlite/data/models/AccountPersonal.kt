package com.alexanderkhyzhun.widrlite.data.models

import android.graphics.Bitmap

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
data class AccountPersonal(
    var uuid: String,
    var firstName: String,
    var lastName: String,
    var phoneNumber: String,
    var email: String,
    var password: String,
    var photo: Bitmap? = null
)