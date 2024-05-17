package com.example.coinpaprika.data.api.api_mappers

import com.example.coinpaprika.data.api.api_data.CoinDetail
import com.example.coinpaprika.data.api.api_data.CoinDetailDto
import com.example.coinpaprika.data.api.api_data.Links
import com.example.coinpaprika.data.api.api_data.LinksDto
import com.example.coinpaprika.data.api.api_data.LinksExtended
import com.example.coinpaprika.data.api.api_data.LinksExtendedDto
import com.example.coinpaprika.data.api.api_data.Tag
import com.example.coinpaprika.data.api.api_data.TagDto
import com.example.coinpaprika.data.api.api_data.Team
import com.example.coinpaprika.data.api.api_data.TeamDto
import com.example.coinpaprika.data.api.api_data.Whitepaper
import com.example.coinpaprika.data.api.api_data.WhitepaperDto
import com.example.coinpaprika.data.api.api_data.getCoinTypeFromString
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class CoinDetailMapper @Inject constructor(
    private val tagMapper: Mapper<Tag, TagDto>,
    private val teamMapper: Mapper<Team, TeamDto>,
    private val linksMapper: Mapper<Links, LinksDto>,
    private val linksExtendedMapper: Mapper<LinksExtended, LinksExtendedDto>,
) : Mapper<CoinDetail, CoinDetailDto> {
    override fun toDomain(data: CoinDetailDto): CoinDetail {
        return CoinDetail(
            id = data.id ?: "",
            name = data.name ?: "",
            symbol = data.symbol ?: "",
            rank = data.rank ?: 0,
            isNew = data.isNew ?: false,
            isActive = data.isActive ?: false,
            type = getCoinTypeFromString(data.type ?: ""),
            logo = data.logo ?: "",
            tags = convertTagDtoList(data.tags),
            team = convertTeamDtoList(data.team),
            description = data.description ?: "",
            message = data.message ?: "",
            openSource = data.openSource ?: false,
            startedAt = data.startedAt?.let { parseStringToOffsetDateTime(it) }
                ?: OffsetDateTime.MIN,
            developmentStatus = data.developmentStatus ?: "",
            hardwareWallet = data.hardwareWallet ?: false,
            proofType = data.proofType ?: "",
            orgStructure = data.orgStructure ?: "",
            hashAlgorithm = data.hashAlgorithm ?: "",
            links = convertLinksDto(data.links),
            linksExtended = convertLinksExtendedDtoList(data.linksExtended),
            whitepaper = convertWhitepaperDto(data.whitepaper),
            firstDataAt = data.firstDataAt?.let { parseStringToOffsetDateTime(it) }
                ?: OffsetDateTime.MIN,
            lastDataAt = data.lastDataAt?.let { parseStringToOffsetDateTime(it) }
                ?: OffsetDateTime.MIN
        )
    }

    private fun convertTagDtoList(tagList: List<TagDto?>?): List<Tag> {
        return tagList?.filterNotNull()?.map { tagMapper.toDomain(it) }
            ?: emptyList()
    }

    private fun convertTeamDtoList(teamList: List<TeamDto?>?): List<Team> {
        return teamList?.filterNotNull()?.map { teamMapper.toDomain(it) }
            ?: emptyList()
    }

    private fun convertLinksDto(linksDto: LinksDto?): Links {
        return linksDto?.let { linksMapper.toDomain(it) } ?: Links(
            explorer = emptyList(),
            facebook = emptyList(),
            reddit = emptyList(),
            sourceCode = emptyList(),
            website = emptyList(),
            youtube = emptyList()
        )
    }

    private fun convertLinksExtendedDtoList(
        linksExtendedList: List<LinksExtendedDto?>?
    ): List<LinksExtended> {
        return linksExtendedList?.filterNotNull()?.map { linksExtendedMapper.toDomain(it) }
            ?: emptyList()
    }

    private fun convertWhitepaperDto(whitepaperDto: WhitepaperDto?): Whitepaper {
        return Whitepaper(
            link = whitepaperDto?.link ?: "",
            thumbnail = whitepaperDto?.thumbnail ?: ""
        )
    }

    private fun parseStringToOffsetDateTime(dateTime: String): OffsetDateTime {
        return try {
            OffsetDateTime.parse(dateTime, DateTimeFormatter.ISO_INSTANT)
        } catch (e: Exception) {
            OffsetDateTime.MIN
        }
    }
}