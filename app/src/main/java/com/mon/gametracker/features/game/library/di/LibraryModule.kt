package com.mon.gametracker.features.game.library.di

import com.mon.gametracker.features.game.library.data.MockGameRepositoryImpl
import com.mon.gametracker.features.game.library.domain.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LibraryModule  {
    @Singleton
    @Provides
    fun provideGameRepository(): GameRepository = MockGameRepositoryImpl()
}