package com.example.coinpaprika.data.api.api_data

enum class CoinType { COIN, TOKEN, UNKNOWN }

fun getCoinTypeFromString(type: String): CoinType {
    return when(type) {
        "coin" -> CoinType.COIN
        "token" -> CoinType.TOKEN
        else -> CoinType.UNKNOWN
    }
}