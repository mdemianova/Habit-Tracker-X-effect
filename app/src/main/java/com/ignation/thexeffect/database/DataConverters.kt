package com.ignation.thexeffect.database

import androidx.room.TypeConverter
import com.ignation.thexeffect.model.Day
import com.ignation.thexeffect.model.DayStatus
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.Types.*
import java.lang.reflect.ParameterizedType

class DataConverters {

    private val moshi = Moshi.Builder().build()
    private val day: ParameterizedType = newParameterizedType(Day::class.java)
    private val jsonAdapter: JsonAdapter<Day> = moshi.adapter(day)

    @TypeConverter
    fun dayToJson(day: Day): String? {
        return jsonAdapter.toJson(day)
    }

    @TypeConverter
    fun jsonToDay(jsonStr: String): Day? {
        return jsonAdapter.fromJson(jsonStr)
    }
//    @TypeConverter
//    fun toDayStatus(value: String) = enumValueOf<DayStatus>(value)
//
//    @TypeConverter
//    fun fromDayStatus(value: DayStatus) = value.name
}