package com.example.lab3.data

import androidx.lifecycle.LiveData

class GroupmatesRepository(private val groupmatesDAO: GroupmatesDAO) {
    val getAllGroupmates: LiveData<List<Groupmates>> = groupmatesDAO.getAllGroupmates()

    suspend fun addGroupmate(groupmate: Groupmates){
        groupmatesDAO.addGroupmate(groupmate)
    }
}