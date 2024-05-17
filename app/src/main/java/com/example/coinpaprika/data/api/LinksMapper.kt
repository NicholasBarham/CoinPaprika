package com.example.coinpaprika.data.api

import com.example.coinpaprika.data.api.api_data.Links
import com.example.coinpaprika.data.api.api_data.LinksDto
import com.example.coinpaprika.data.api.api_data.Tag
import com.example.coinpaprika.data.api.api_data.TagDto
import com.example.coinpaprika.data.api.api_data.Team
import com.example.coinpaprika.data.api.api_data.TeamDto

class LinksMapper : Mapper<Links, LinksDto> {
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