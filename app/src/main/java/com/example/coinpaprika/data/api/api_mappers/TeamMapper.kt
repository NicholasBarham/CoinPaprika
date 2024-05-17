package com.example.coinpaprika.data.api.api_mappers

import com.example.coinpaprika.data.api.api_data.Team
import com.example.coinpaprika.data.api.api_data.TeamDto
import javax.inject.Inject

class TeamMapper @Inject constructor() : Mapper<Team, TeamDto> {
    override fun toDomain(data: TeamDto): Team {
        return Team(
            id = data.id ?: "",
            name = data.name ?: "",
            position = data.position ?: ""
        )
    }
}