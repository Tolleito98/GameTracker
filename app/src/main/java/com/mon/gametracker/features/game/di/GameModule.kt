package com.mon.gametracker.features.game.di

import com.mon.gametracker.features.game.data.GameRepositoryImpl
import com.mon.gametracker.features.game.data.MockAchievementRepositoryImpl
import com.mon.gametracker.features.game.data.MockGameRepositoryImpl
import com.mon.gametracker.features.game.data.remote.GameApiService
import com.mon.gametracker.features.game.domain.achievement.AchievementRepository
import com.mon.gametracker.features.game.domain.game.GameRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GameModule  {

//    @Singleton
//    @Provides
//    fun  provideGameRepository(): GameRepository = MockGameRepositoryImpl()

    @Singleton
    @Provides
    fun provideGameRepository(api: GameApiService): GameRepository = GameRepositoryImpl(
        api = api
    )

    @Singleton
    @Provides
    fun provideAchievementRepository(): AchievementRepository = MockAchievementRepositoryImpl()


}