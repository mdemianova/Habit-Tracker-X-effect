package com.ignation.thexeffect.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ignation.thexeffect.data.local.entities.BoardEntity
import com.ignation.thexeffect.data.local.entities.WeekEntity

@Database(
    entities = [
        BoardEntity::class,
        WeekEntity::class
    ], version = 6, exportSchema = false
)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
}