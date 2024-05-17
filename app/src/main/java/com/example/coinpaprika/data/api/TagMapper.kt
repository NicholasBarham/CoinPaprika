package com.example.coinpaprika.data.api

import com.example.coinpaprika.data.api.api_data.Tag
import com.example.coinpaprika.data.api.api_data.TagDto

class TagMapper : Mapper<Tag, TagDto> {
    override fun toDomain(data: TagDto): Tag {
        return Tag(
            id = data.id ?: "",
            name = data.name ?: "",
            coinCounter = data.coinCounter ?: 0,
            icoCounter = data.icoCounter ?: 0
        )
    }
}