package com.ignation.thexeffect.data.local.converters

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun calendarToDate(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun dateToCalendar(date: Long): Calendar {
        return Calendar.getInstance().apply { timeInMillis = date }
    }
}