package com.example.coinpaprika.data.api.api_data

import java.time.OffsetDateTime

data class CoinDetail(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val isNew: Boolean,
    val isActive: Boolean,
    val type: CoinType,
    val logo: String,
    val tags: List<Tag>,
    val team: List<Team>,
    val description: String,
    val message: String,
    val openSource: Boolean,
    val startedAt: OffsetDateTime,
    val developmentStatus: String,
    val hardwareWallet: Boolean,
    val proofType: String,
    val orgStructure: String,
    val hashAlgorithm: String,
    val links: Links,
    val linksExtended: List<LinksExtended>,
    val whitepaper: Whitepaper,
    val firstDataAt: OffsetDateTime,
    val lastDataAt: OffsetDateTime
)