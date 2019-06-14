package com.alexanderkhyzhun.widrlite.data.models.response

import com.google.gson.annotations.SerializedName

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
data class RPNotification(
    @SerializedName("id")
    val id: String,
    @SerializedName("nodeId")
    val nodeId: String,
    @SerializedName("senderId")
    val senderID: String,
    @SerializedName("jsonDate")
    val jsonDate: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("when")
    val `when`: String,
    @SerializedName("isNew")
    val isNew: Boolean
)