package com.example.lab3.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface GroupmatesDAO {
    @Query("SELECT * FROM groupmates")
    fun getAllGroupmates(): List<Groupmates>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGroupmate(groupmate: Groupmates)

    @Query("DELETE FROM groupmates")
    fun deleteAll()
}