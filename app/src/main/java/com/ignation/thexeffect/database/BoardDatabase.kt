package com.ignation.thexeffect.database

import androidx.room.Database
import androidx.room.RoomDatabase
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
abstract class BoardDatabase : RoomDatabase() {
    abstract fun boardDao(): BoardDao
}