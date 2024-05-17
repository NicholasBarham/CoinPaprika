package com.example.coinpaprika.data.api.api_data

data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val isNew: Boolean,
    val isActive: Boolean,
    val coinType: CoinType
)
