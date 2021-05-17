package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.room.Room
import com.example.lab3.data.Groupmates
import com.example.lab3.data.GroupmatesDatabase
import kotlinx.coroutines.*
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var db: GroupmatesDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = Room.databaseBuilder(
            applicationContext,
            GroupmatesDatabase::class.java,
            "groupmate-db"
        ).allowMainThreadQueries()
            .build()
    }
    fun addToBase(view: View) {
        db.groupmatesDAO().addGroupmate(Groupmates(0,"Имя Фамилия Отчество", Calendar.getInstance().time.toString()))
    }

    fun getBase(view: View) {
        val groupmate = db.groupmatesDAO().getAllGroupmates()
        println(groupmate)
    }

    fun deleteGroupmates(view: View) {
        db.groupmatesDAO().deleteAll()
    }


}