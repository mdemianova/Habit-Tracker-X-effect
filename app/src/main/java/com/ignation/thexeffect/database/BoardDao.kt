package com.ignation.thexeffect.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ignation.thexeffect.model.Day

@Dao
interface BoardDao {
    @Insert
    suspend fun insert(day: Day)

    @Query("SELECT * FROM boards WHERE id = :id")
    fun getDayById(id: Long): Day
}