package com.example.lab3.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface GroupmatesDAO {
    @Query("SELECT * FROM groupmates")
    fun getAllGroupmates(): LiveData<List<Groupmates>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGroupmate(groupmate: Groupmates)
}