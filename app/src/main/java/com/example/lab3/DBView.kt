package com.example.lab3

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.app.NotificationCompat
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
        notification()

    }

    fun notification(){
        val groupmateLen = DBConnect.db.groupmatesDAO().getAllGroupmates().size
        val NOTIFY_ID: Int = 100;
        val notificationIntent = Intent(this, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT
        )
        val builder = NotificationCompat.Builder(this,"CHANNEL_ID")
        builder.setContentIntent(contentIntent)
            .setSmallIcon(android.R.drawable.alert_dark_frame)
            .setContentTitle("Количество одногруппников в базе")
            .setContentText(groupmateLen.toString())
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFY_ID, builder.build())

    }


}