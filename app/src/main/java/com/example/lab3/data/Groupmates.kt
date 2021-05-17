package com.example.lab3.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "groupmates")
data class Groupmates(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "fio")
    val fio: String,
    @ColumnInfo(name = "dateOfCreate")
    val dateOfCreate: String

    )