package com.ignation.thexeffect.data.local.converters

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate

class Converters {
    @TypeConverter
    fun localDateToString(localDate: LocalDate): String {
        return localDate.toString()
    }

    @TypeConverter
    fun stringToLocalDate(string: String): LocalDate {
        val (y, m, d) = string.split("-").map { it.toInt() }
        return LocalDate(y, m, d)
    }
}