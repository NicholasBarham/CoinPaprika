package com.example.coinpaprika.data.api

import com.example.coinpaprika.data.api.api_data.ApiError
import com.example.coinpaprika.data.api.api_data.Coin
import com.example.coinpaprika.data.api.api_data.CoinDetail
import com.example.coinpaprika.data.api.api_data.Result
import kotlinx.coroutines.flow.Flow

interface CoinManager {
    suspend fun getAllCoins(): Flow<Result<List<Coin>, ApiError>>

    suspend fun getCoinById(id: String): Flow<Result<CoinDetail, ApiError>>
}