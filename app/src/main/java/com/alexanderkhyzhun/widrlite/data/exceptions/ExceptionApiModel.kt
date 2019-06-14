package com.alexanderkhyzhun.widrlite.data.exceptions

import com.google.gson.annotations.SerializedName

data class ExceptionApiModel(
    @SerializedName("code") val type: String,
    @SerializedName("error") val message: String
)