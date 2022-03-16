package com.ignation.thexeffect.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ignation.thexeffect.database.entities.Board
import com.ignation.thexeffect.database.entities.Day
import com.ignation.thexeffect.database.entities.Week

@Database(
    entities = [
        Board::class,
        Week::class,
        Day::class
    ], version = 1, exportSchema = false
)
abstract class BoardRoomDatabase : RoomDatabase() {
    abstract fun boardDao(): BoardDao

    companion object {
        @Volatile
        private var INSTANCE: BoardRoomDatabase? = null
        fun getDatabase(context: Context): BoardRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BoardRoomDatabase::class.java,
                    "board_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}