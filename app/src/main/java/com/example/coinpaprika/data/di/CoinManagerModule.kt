package com.example.coinpaprika.data.di

import com.example.coinpaprika.data.api.CoinManager
import com.example.coinpaprika.data.api.CoinManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoinManagerModule {
    @Binds
    abstract fun bindCoinManager(coinManager: CoinManagerImpl): CoinManager
}