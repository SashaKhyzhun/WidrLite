package com.alexanderkhyzhun.widrlite.data.models.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 *
 *
 * Requesting a list of notification by user id
 */
data class NWNotification(
    @Expose
    @SerializedName("userId")
    val userId: String = UUID.randomUUID().toString()
)