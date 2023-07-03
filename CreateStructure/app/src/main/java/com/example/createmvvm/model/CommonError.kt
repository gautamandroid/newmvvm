package com.example.createmvvm.model

import com.google.gson.annotations.SerializedName

data class CommonError(
    @SerializedName("detail")
    val detail: String? = null,
)