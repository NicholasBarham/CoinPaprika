package com.example.coinpaprika.data.api

import com.example.coinpaprika.data.api.api_data.Tag
import com.example.coinpaprika.data.api.api_data.TagDto
import com.example.coinpaprika.data.api.api_data.Team
import com.example.coinpaprika.data.api.api_data.TeamDto

class TeamMapper : Mapper<Team, TeamDto> {
    override fun toDomain(data: TeamDto): Team {
        return Team(
            id = data.id ?: "",
            name = data.name ?: "",
            position = data.position ?: ""
        )
    }
}