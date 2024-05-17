package com.example.coinpaprika.data.api.api_data


import com.google.gson.annotations.SerializedName

data class WhitepaperDto(
    @SerializedName("link")
    val link: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?
)