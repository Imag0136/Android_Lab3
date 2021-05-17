package com.example.lab3.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroupmateViewModel(application: Application): AndroidViewModel(application) {
    private val readAllGroupmates: LiveData<List<Groupmates>>
    private val repository: GroupmatesRepository

    init {
        val groupmatesDAO = GroupmatesDatabase.getDatabase(application).groupmatesDAO()
        repository = GroupmatesRepository(groupmatesDAO)
        readAllGroupmates = repository.getAllGroupmates
    }

    fun addGroupmate(groupmate: Groupmates) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addGroupmate(groupmate)
        }
    }
}
