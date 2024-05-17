package com.example.coinpaprika.data.api

import com.example.coinpaprika.data.api.api_data.CoinDetailDto
import com.example.coinpaprika.data.api.api_data.CoinDto
import retrofit2.Response
import retrofit2.http.GET

interface CoinApiService {
    @GET("coins")
    suspend fun getAllCoins(): Response<List<CoinDto>>


    @GET("coins/{coinId}")
    suspend fun getCoin(coinId: String): Response<CoinDetailDto>

}