package com.example.lab3.data

import androidx.annotation.NonNull
import androidx.room.*
import java.util.*

@Entity(tableName = "groupmates")
data class Groupmates(
    @PrimaryKey
    val uuid: String,
    @ColumnInfo(name = "fio")
    val fio: String,
    @ColumnInfo(name = "dateOfCreate")
    val dateOfCreate: String

    )