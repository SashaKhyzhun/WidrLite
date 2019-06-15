package com.alexanderkhyzhun.widrlite.data.models.response

import com.google.gson.annotations.SerializedName

data class RPNewsItem(
    @SerializedName("nodeId")
    val nodeId: String,
    @SerializedName("postId")
    val postId: String,
    @SerializedName("postBody")
    val postBody: String,
    @SerializedName("postTime")
    val postTime: String,
    @SerializedName("postStatus")
    val postStatus: String,
    @SerializedName("senderId")
    val senderId: String,
    @SerializedName("senderName")
    val senderName: String,
    @SerializedName("senderPhoto")
    val senderPhoto: String,
    @SerializedName("senderLocation")
    val senderLocation: String,
    @SerializedName("bgColor")
    val bgColor: String
)
