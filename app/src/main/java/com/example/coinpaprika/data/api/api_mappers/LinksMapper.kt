package com.example.coinpaprika.data.api.api_mappers

import com.example.coinpaprika.data.api.api_data.Links
import com.example.coinpaprika.data.api.api_data.LinksDto
import javax.inject.Inject

class LinksMapper @Inject constructor() : Mapper<Links, LinksDto> {
    override fun toDomain(data: LinksDto): Links {
        return Links(
            explorer = data.explorer?.filterNotNull() ?: emptyList(),
            facebook = data.facebook?.filterNotNull() ?: emptyList(),
            reddit = data.reddit?.filterNotNull() ?: emptyList(),
            sourceCode = data.sourceCode?.filterNotNull() ?: emptyList(),
            website = data.website?.filterNotNull() ?: emptyList(),
            youtube = data.youtube?.filterNotNull() ?: emptyList()
        )
    }
}