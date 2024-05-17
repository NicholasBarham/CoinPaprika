package com.example.coinpaprika.data.api

import com.example.coinpaprika.data.api.api_data.CoinDetailDto
import com.example.coinpaprika.data.api.api_data.CoinDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApiService {
    @GET("coins")
    suspend fun getAllCoins(): Response<List<CoinDto>>


    @GET("coins/{coinId}")
    suspend fun getCoin(
        @Path("coinId") coinId: String
    ): Response<CoinDetailDto>

}