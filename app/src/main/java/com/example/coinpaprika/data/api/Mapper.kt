package com.example.coinpaprika.data.api

interface Mapper <DOMAIN, DATA> {
    fun toDomain(data: DATA): DOMAIN
}