package com.alexanderkhyzhun.widrlite.data.models.response

import com.google.gson.annotations.SerializedName

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
data class RPMessage(
    @SerializedName("senderId")
    val senderId: String,
    @SerializedName("nodePhoto")
    val senderPhoto: String,
    @SerializedName("senderName")
    val senderName: String,
    @SerializedName("senderLocation")
    val senderLocation: String,
    @SerializedName("senderJob")
    val senderJob: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("amount")
    val amount: String,
    @SerializedName("isNew")
    val isNew: Boolean
)