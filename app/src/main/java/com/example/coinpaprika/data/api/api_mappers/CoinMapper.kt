package com.example.coinpaprika.data.api.api_mappers

import com.example.coinpaprika.data.api.api_data.Coin
import com.example.coinpaprika.data.api.api_data.CoinDto
import com.example.coinpaprika.data.api.api_data.getCoinTypeFromString
import javax.inject.Inject

class CoinMapper @Inject constructor() : Mapper<Coin, CoinDto> {
    override fun toDomain(data: CoinDto): Coin {
        return Coin(
            id = data.id ?: "",
            name = data.name ?: "",
            symbol = data.symbol ?: "",
            rank = data.rank ?: 0,
            isNew = data.isNew ?: false,
            isActive = data.isActive ?: false,
            coinType = getCoinTypeFromString(data.type ?: "")
        )
    }

}