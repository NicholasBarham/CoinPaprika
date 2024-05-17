package com.example.coinpaprika.data.api

import retrofit2.Response
import retrofit2.http.GET

interface CoinApiService {
    @GET("coins")
    suspend fun getAllCoins(): Response<CoinsDto>

}