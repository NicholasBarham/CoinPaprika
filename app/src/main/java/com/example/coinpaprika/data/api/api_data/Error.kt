package com.example.coinpaprika.data.api.api_data

sealed interface Error

enum class ApiError : Error {
    BAD_REQUEST,
    PAYMENT_REQUIRED,
    FORBIDDEN,
    NOT_FOUND,
    TOO_MANY_REQUESTS,
    INTERNAL_SERVER_ERROR,
    RETURNING_NULL,
    NO_INTERNET,
    UNKNOWN
}