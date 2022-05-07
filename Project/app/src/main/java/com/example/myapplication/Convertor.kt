package com.example.myapplication

import androidx.room.TypeConverter
import java.util.*

class Convertor {

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }



}