package com.example.coinpaprika.data.api.api_mappers

import com.example.coinpaprika.data.api.api_data.LinksExtended
import com.example.coinpaprika.data.api.api_data.LinksExtendedDto
import com.example.coinpaprika.data.api.api_data.Stats
import com.example.coinpaprika.data.api.api_data.StatsDto
import javax.inject.Inject

class LinksExtendedMapper @Inject constructor() : Mapper<LinksExtended, LinksExtendedDto> {

    override fun toDomain(data: LinksExtendedDto): LinksExtended {
        return LinksExtended(
            url = data.url ?: "",
            type = data.type ?: "",
            stats = convertStatsFromStatsDto(data.stats)
        )
    }

    private fun convertStatsFromStatsDto(statsDto: StatsDto?): Stats {
        return Stats(
            subscribers = statsDto?.subscribers ?: 0,
            contributors = statsDto?.contributors ?: 0,
            stars = statsDto?.stars ?: 0,
            followers = statsDto?.followers ?: 0
        )
    }
}