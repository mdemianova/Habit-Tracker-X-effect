package com.ignation.thexeffect.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [BoardDatabase::class], version = 1, exportSchema = false)
@TypeConverters(DataConverters::class)
abstract class BoardRoomDatabase : RoomDatabase() {
    abstract fun boardDao(): BoardDao
    companion object {
        @Volatile
        private var INSTANCE: BoardRoomDatabase? = null
        fun getDatabase(context: Context): BoardRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val roomConverter = DataConverters()
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BoardRoomDatabase::class.java,
                    "board_database"
                )
                    .fallbackToDestructiveMigration()
                    .addTypeConverter(roomConverter)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}