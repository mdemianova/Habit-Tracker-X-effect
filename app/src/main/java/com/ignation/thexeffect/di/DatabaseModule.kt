package com.ignation.thexeffect.di

import android.content.Context
import androidx.room.Room
import com.ignation.thexeffect.database.BoardDatabase
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
        BoardDatabase::class.java,
        "board_db"
    ).build()

    @Singleton
    @Provides
    fun provideBoardDao(db: BoardDatabase) = db.boardDao()
}