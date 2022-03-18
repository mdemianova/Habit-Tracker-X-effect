package com.ignation.thexeffect.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ignation.thexeffect.database.entities.BoardDatabase
import com.ignation.thexeffect.database.entities.DayDatabase
import com.ignation.thexeffect.database.entities.WeekDatabase

@Database(
    entities = [
        BoardDatabase::class,
        WeekDatabase::class,
        DayDatabase::class
    ], version = 3, exportSchema = false
)
abstract class BoardRoomDatabase : RoomDatabase() {
    abstract fun boardDao(): BoardDao
}