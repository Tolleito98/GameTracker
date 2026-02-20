package com.mon.gametracker.features.game.core.di

import android.content.Context
import androidx.room.Room
import com.mon.gametracker.features.game.core.data.local.GameDAO
import com.mon.gametracker.features.game.core.data.local.GameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GameDatabase {
        return Room.databaseBuilder(
            context,
            GameDatabase::class.java,
            "game_tracker_db"
        ).build()
    }

    @Provides
    fun provideGameDao(db: GameDatabase): GameDAO = db.gameDao()
}