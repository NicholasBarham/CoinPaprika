package com.example.coinpaprika.data.di

import com.example.coinpaprika.data.api.api_data.Coin
import com.example.coinpaprika.data.api.api_data.CoinDetail
import com.example.coinpaprika.data.api.api_data.CoinDetailDto
import com.example.coinpaprika.data.api.api_data.CoinDto
import com.example.coinpaprika.data.api.api_data.Links
import com.example.coinpaprika.data.api.api_data.LinksDto
import com.example.coinpaprika.data.api.api_data.LinksExtended
import com.example.coinpaprika.data.api.api_data.LinksExtendedDto
import com.example.coinpaprika.data.api.api_data.Tag
import com.example.coinpaprika.data.api.api_data.TagDto
import com.example.coinpaprika.data.api.api_data.Team
import com.example.coinpaprika.data.api.api_data.TeamDto
import com.example.coinpaprika.data.api.api_mappers.CoinDetailMapper
import com.example.coinpaprika.data.api.api_mappers.CoinMapper
import com.example.coinpaprika.data.api.api_mappers.LinksExtendedMapper
import com.example.coinpaprika.data.api.api_mappers.LinksMapper
import com.example.coinpaprika.data.api.api_mappers.Mapper
import com.example.coinpaprika.data.api.api_mappers.TagMapper
import com.example.coinpaprika.data.api.api_mappers.TeamMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MappersModule {
    @Binds
    abstract fun bindCoinMapper(coinMapper: CoinMapper): Mapper<Coin, CoinDto>

    @Binds
    abstract fun bindCoinDetailMapper(coinDetailMapper: CoinDetailMapper): Mapper<CoinDetail, CoinDetailDto>

    @Binds
    abstract fun bindLinksExtendedMapper(linksExtendedMapper: LinksExtendedMapper): Mapper<LinksExtended, LinksExtendedDto>

    @Binds
    abstract fun bindLinksMapper(linksMapper: LinksMapper): Mapper<Links, LinksDto>

    @Binds
    abstract fun bindTagMapper(tagMapper: TagMapper): Mapper<Tag, TagDto>

    @Binds
    abstract fun bindTeamMapper(teamMapper: TeamMapper): Mapper<Team, TeamDto>
}