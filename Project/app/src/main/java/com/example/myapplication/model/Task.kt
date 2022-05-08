package com.example.myapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.myapplication.Convertor
import java.util.*

@Entity(tableName = "tasksTable")
class Task(@ColumnInfo(name = "taskName")val taskName:String,
           @ColumnInfo(name = "taskType")val taskType:String,
           @ColumnInfo(name = "dueDate")val dueDate:String,
           //@ColumnInfo(name = "createdAt")val createdAt:Date,
           @ColumnInfo(name = "to_do")val toDo:Boolean,
           @ColumnInfo(name = "in_progress")val inProgress:Boolean,
           @ColumnInfo(name = "done")val done:Boolean,
           @ColumnInfo(name = "description")val description:String) {
    @PrimaryKey(autoGenerate = true)
    var taskId = 0

    // might need a toString function
}