package com.example.myapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.myapplication.Convertor
import java.util.*

@Entity(tableName = "tasksTable")
@TypeConverters(Convertor::class)
class Task(@ColumnInfo(name = "taskName")val taskName:String,
           @ColumnInfo(name = "taskType")val taskType:String,
           @ColumnInfo(name = "taskStatus")val taskStatus:String,
           @ColumnInfo(name = "description")val description:String,
           @ColumnInfo(name = "dueDate")val dueDate:Date) {
    @PrimaryKey(autoGenerate = true)
    var taskId = 0
}