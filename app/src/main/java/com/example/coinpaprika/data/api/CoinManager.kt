package com.example.coinpaprika.data.api

import com.example.coinpaprika.data.api.api_data.ApiError
import com.example.coinpaprika.data.api.api_data.Coin
import com.example.coinpaprika.data.api.api_data.CoinDetail
import com.example.coinpaprika.data.api.api_data.Result

interface CoinManager {
    suspend fun getAllCoins(): Result<List<Coin>, ApiError>

    suspend fun getCoinById(id: String): Result<CoinDetail, ApiError>
}