package com.mon.gametracker.features.game.core.di

import com.mon.gametracker.features.game.core.data.GameRepositoryImpl
import com.mon.gametracker.features.game.core.data.MockAchievementRepositoryImpl
import com.mon.gametracker.features.game.core.data.MockGameRepositoryImpl
import com.mon.gametracker.features.game.core.data.local.GameDAO
import com.mon.gametracker.features.game.core.data.remote.AchievementRepositoryImpl
import com.mon.gametracker.features.game.core.data.remote.GameApiService
import com.mon.gametracker.features.game.core.domain.achievement.AchievementRepository
import com.mon.gametracker.features.game.core.domain.game.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GameModule {

//    @Singleton
//    @Provides
//    fun  provideGameRepository(): GameRepository = MockGameRepositoryImpl()

    @Singleton
    @Provides
    fun provideGameRepository(
        api: GameApiService,
        dao: GameDAO
    ): GameRepository =
        GameRepositoryImpl(
            api = api,
            dao = dao
        )

//    @Singleton
//    @Provides
//    fun provideAchievementRepository(): AchievementRepository = MockAchievementRepositoryImpl()

    @Singleton
    @Provides
    fun provideAchievementRepository(api: GameApiService): AchievementRepository =
        AchievementRepositoryImpl(api = api)

}