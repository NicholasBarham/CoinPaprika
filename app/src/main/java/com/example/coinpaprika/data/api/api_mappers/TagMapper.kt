package com.example.coinpaprika.data.api.api_mappers

import com.example.coinpaprika.data.api.api_data.Tag
import com.example.coinpaprika.data.api.api_data.TagDto
import javax.inject.Inject

class TagMapper @Inject constructor() : Mapper<Tag, TagDto> {
    override fun toDomain(data: TagDto): Tag {
        return Tag(
            id = data.id ?: "",
            name = data.name ?: "",
            coinCounter = data.coinCounter ?: 0,
            icoCounter = data.icoCounter ?: 0
        )
    }
}