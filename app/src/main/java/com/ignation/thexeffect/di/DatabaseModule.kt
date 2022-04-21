package com.ignation.thexeffect.di

import android.content.Context
import androidx.room.Room
import com.ignation.thexeffect.data.local.HabitDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideBoardDatabase(
        @ApplicationContext
        app: Context
    ) = Room.databaseBuilder(
        app,
        HabitDatabase::class.java,
        "board_db"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideBoardDao(db: HabitDatabase) = db.habitDao()
}