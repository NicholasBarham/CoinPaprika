package com.example.coinpaprika

import com.example.coinpaprika.data.api.CoinApiService
import com.example.coinpaprika.data.api.CoinManagerImpl
import com.example.coinpaprika.data.api.api_data.Coin
import com.example.coinpaprika.data.api.api_data.CoinDetail
import com.example.coinpaprika.data.api.api_data.CoinDetailDto
import com.example.coinpaprika.data.api.api_data.CoinDto
import com.example.coinpaprika.data.api.api_data.CoinType
import com.example.coinpaprika.data.api.api_data.Result
import com.example.coinpaprika.data.api.api_mappers.Mapper
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import retrofit2.Response

class CoinManagerImplTest {
    @Test
    fun test_sortingCoinNamesCorrectly() {
        val coinApi = mock<CoinApiService>()
        val coinMapper = mock<Mapper<Coin, CoinDto>>()
        val coinDetailMapper = mock<Mapper<CoinDetail, CoinDetailDto>>()

        val unorderedCoinDtoList = listOf(
            createCoinDtoByName("DDD"),
            createCoinDtoByName("VVV"),
            createCoinDtoByName("AAA"),
            createCoinDtoByName("HHH"),
            createCoinDtoByName("WWW"),
        )

        runBlocking {
            Mockito.`when`(coinApi.getAllCoins())
                .thenReturn(
                    Response.success(unorderedCoinDtoList)
                )
        }

        runBlocking {
            Mockito.`when`(
                coinMapper.toDomain(
                    this@CoinManagerImplTest.any(CoinDto::class.java)
                )
            ).thenAnswer {
                val coinDto = it.arguments[0] as CoinDto

                Coin(
                    id = coinDto.id!!,
                    name = coinDto.name!!,
                    symbol = coinDto.symbol!!,
                    rank = coinDto.rank!!,
                    isNew = coinDto.isNew!!,
                    isActive = coinDto.isActive!!,
                    coinType = CoinType.COIN
                )
            }
        }

        val uut = CoinManagerImpl(
            coinApi = coinApi,
            coinMapper = coinMapper,
            coinDetailMapper = coinDetailMapper,
        )

        runTest {
            uut.getAllCoins().collect {
                assert(it is Result.Success)
                val coins = (it as Result.Success).data
                assert(coins.size == 5)
                assert(coins[0].name == "AAA")
                assert(coins[1].name == "DDD")
                assert(coins[2].name == "HHH")
                assert(coins[3].name == "VVV")
                assert(coins[4].name == "WWW")
            }
        }
    }

    private fun createCoinDtoByName(name: String) = CoinDto(
        id = "id",
        name = name,
        symbol = "COIN",
        rank = 1,
        isNew = false,
        isActive = true,
        type = "coin"
    )

    private fun <T> any(type: Class<T>): T = Mockito.any<T>(type)
}