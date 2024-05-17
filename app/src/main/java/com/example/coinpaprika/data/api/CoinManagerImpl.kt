package com.example.coinpaprika.data.api

import com.example.coinpaprika.data.api.api_data.ApiError
import com.example.coinpaprika.data.api.api_data.Coin
import com.example.coinpaprika.data.api.api_data.CoinDetail
import com.example.coinpaprika.data.api.api_data.CoinDetailDto
import com.example.coinpaprika.data.api.api_data.CoinDto
import com.example.coinpaprika.data.api.api_data.Result
import com.example.coinpaprika.data.api.api_mappers.Mapper
import java.net.UnknownHostException
import javax.inject.Inject

class CoinManagerImpl @Inject constructor(
    private val coinApi: CoinApiService,
    private val coinMapper: Mapper<Coin, CoinDto>,
    private val coinDetailMapper: Mapper<CoinDetail, CoinDetailDto>
) : CoinManager {
    override suspend fun getAllCoins(): Result<List<Coin>, ApiError> {
        return try {
            val response = coinApi.getAllCoins()
            if (response.isSuccessful) {
                response.body()?.let { coinDtoList ->
                    val coins = coinDtoList.map { coinMapper.toDomain(it) }
                    Result.Success(coins)
                } ?: Result.Error(ApiError.RETURNING_NULL)
            } else {
                val apiError = getApiErrorFromCode(response.code())
                Result.Error(apiError)
            }
        } catch (e: UnknownHostException) {
            println("ERROR: $e")
            Result.Error(ApiError.NO_INTERNET)
        } catch (e: Exception) {
            println("ERROR: $e")
            Result.Error(ApiError.UNKNOWN)
        }
    }

    override suspend fun getCoinById(id: String): Result<CoinDetail, ApiError> {
        return try {
            val response = coinApi.getCoin(id)

            if (response.isSuccessful) {
                response.body()?.let { detailDto ->
                    val detail = coinDetailMapper.toDomain(detailDto)
                    return Result.Success(detail)
                } ?: return Result.Error(ApiError.RETURNING_NULL)
            } else {
                val apiError = getApiErrorFromCode(response.code())
                return Result.Error(apiError)
            }
        } catch (e: UnknownHostException) {
            println("ERROR: $e")
            Result.Error(ApiError.NO_INTERNET)
        } catch (e: Exception) {
            println("ERROR: $e")
            Result.Error(ApiError.UNKNOWN)
        }
    }

    private fun getApiErrorFromCode(code: Int): ApiError {
        return when (code) {
            400 -> ApiError.BAD_REQUEST
            402 -> ApiError.PAYMENT_REQUIRED
            403 -> ApiError.FORBIDDEN
            404 -> ApiError.NOT_FOUND
            429 -> ApiError.TOO_MANY_REQUESTS
            500 -> ApiError.INTERNAL_SERVER_ERROR
            else -> ApiError.UNKNOWN
        }
    }
}