package com.example.coinpaprika.data.api.api_mappers

interface Mapper <DOMAIN, DATA> {
    fun toDomain(data: DATA): DOMAIN
}