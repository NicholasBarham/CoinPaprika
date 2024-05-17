package com.example.coinpaprika.data.api.api_data


import com.google.gson.annotations.SerializedName

data class LinksExtendedDto(
    @SerializedName("url")
    val url: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("stats")
    val stats: StatsDto?
)