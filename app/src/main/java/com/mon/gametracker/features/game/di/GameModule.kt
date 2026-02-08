package com.mon.gametracker.features.game.di

import com.mon.gametracker.features.game.data.MockGameRepositoryImpl
import com.mon.gametracker.features.game.domain.game.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GameModule  {
    @Singleton
    @Provides
    fun provideGameRepository(): GameRepository = MockGameRepositoryImpl()
}