package com.example.lab3

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.room.Room
import com.example.lab3.data.Groupmates
import com.example.lab3.data.GroupmatesDatabase
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DBConnect.db = Room.databaseBuilder(
            applicationContext,
            GroupmatesDatabase::class.java,
            "groupmate-db"
        ).allowMainThreadQueries()
            .build()
        createNotificationChannel()
       // DBConnect.db.clearAllTables()
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "CHANNEL_ID"
            val descriptionText = "getString(R.string.channel_description)"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {
                description = descriptionText
            }
            channel.vibrationPattern = longArrayOf(3000)
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    fun addToBase(view: View) {
        var texts = findViewById<EditText>(R.id.editTextHello)
        if (texts.text.isEmpty())
            Toast.makeText(this,"Пустая сторока!!", Toast.LENGTH_LONG).show()
        else
            DBConnect.db.groupmatesDAO().addGroupmate(Groupmates(UUID.randomUUID().toString(),texts.text.toString(), Calendar.getInstance().time.toString()))
            texts.text.clear()
    }

    fun getBase(view: View) {
//        val groupmate = DBConnect.db.groupmatesDAO().getAllGroupmates()
//        println(groupmate)
        var a = Intent(this, DBView::class.java)
        startActivity(a)
    }

    fun deleteGroupmates(view: View) {
        DBConnect.db.groupmatesDAO().deleteAll()
    }

    //Кидать уведомление лучше при переходе в отображение списка из базы
    fun notification(view: View){
        val groupmateLen = DBConnect.db.groupmatesDAO().getAllGroupmates().size
        val NOTIFY_ID: Int = 100;
        val notificationIntent = Intent(this, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent,PendingIntent.FLAG_CANCEL_CURRENT
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