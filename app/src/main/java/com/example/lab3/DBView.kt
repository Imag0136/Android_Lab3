package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.lab3.data.Groupmates
import kotlin.reflect.typeOf

class DBView : AppCompatActivity() {
    lateinit var groupmate: List<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbview)
        groupmate = DBConnect.db.groupmatesDAO().getAllGroupmates().map { groupmates -> groupmates.fio }

        var listView = findViewById<ListView>(R.id.groupmateList)

        var adapter:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,groupmate)
        listView.adapter = adapter




    }


}