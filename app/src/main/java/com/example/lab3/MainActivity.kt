package com.example.lab3

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.room.Room
import com.example.lab3.data.Groupmates
import com.example.lab3.data.GroupmatesDatabase
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    private val NOTIFICATION = "NOTIFICATION"

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //DB connect
        DBConnect.db = Room.databaseBuilder(
            applicationContext,
            GroupmatesDatabase::class.java,
            "groupmate-db"
        ).allowMainThreadQueries()
            .build()
        //Сохранение настроек
        prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)

        val checkBox = findViewById<CheckBox>(R.id.notification_checkBox)
        val editor = prefs.edit()
        checkBox.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked){
                editor.putBoolean(NOTIFICATION,checkBox.isChecked).commit()
                createNotificationChannel()
            }else{
                editor.putBoolean(NOTIFICATION,checkBox.isChecked).commit()
                deleteNotificationChanel()
            }
        }
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
    private fun deleteNotificationChanel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.deleteNotificationChannel("CHANNEL_ID")
        }
    }

//    fun createNotificate(view: View) {
//        val checkBox = findViewById<CheckBox>(R.id.notification_checkBox)
//        val editor = prefs.edit()
//        checkBox.setOnCheckedChangeListener{ buttonView, isChecked ->
//            if (isChecked){
//                editor.putBoolean(NOTIFICATION,checkBox.isChecked).commit()
//                createNotificationChannel()
//            }else{
//                editor.putBoolean(NOTIFICATION,checkBox.isChecked).commit()
//                deleteNotificationChanel()
//            }
//        }
//    }


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

    override fun onDestroy() {
        val checkBox = findViewById<CheckBox>(R.id.notification_checkBox)
        val editor = prefs.edit()
        editor.putBoolean(NOTIFICATION,checkBox.isChecked).commit()
        deleteNotificationChanel()

//        checkBox.setOnCheckedChangeListener{ buttonView, isChecked ->
//            if (isChecked){
//                println(isChecked)
//                editor.putBoolean(NOTIFICATION, true).commit()
//            }else{
//                println(isChecked)
//                editor.putBoolean(NOTIFICATION, false).commit()
//            }
//        }
       // println(prefs.getBoolean(NOTIFICATION,true))

        super.onDestroy()

    }

    override fun onStart() {
        super.onStart()
        val checkBox = findViewById<CheckBox>(R.id.notification_checkBox)
        if (prefs.contains(NOTIFICATION)) {
            println("Настройка есть")
            if (prefs.getBoolean(NOTIFICATION, false)) {
                checkBox.isChecked = prefs.getBoolean(NOTIFICATION, false)
                createNotificationChannel()
                println("Настройка TRUE")
                // println(prefs.getBoolean(NOTIFICATION,false))}
            } else {
                println("Настройка FALSE")
                checkBox.isChecked = false
                //deleteNotificationChanel()
            }
        } else {
            println("Настройки нет")
            checkBox.isChecked = false
        }
    }


//    //Кидать уведомление лучше при переходе в отображение списка из базы
//    fun notification(){
//        val groupmateLen = DBConnect.db.groupmatesDAO().getAllGroupmates().size
//        val NOTIFY_ID: Int = 100;
//        val notificationIntent = Intent(this, MainActivity::class.java)
//        val contentIntent = PendingIntent.getActivity(
//            this,
//            0, notificationIntent,PendingIntent.FLAG_CANCEL_CURRENT
//        )
//        val builder = NotificationCompat.Builder(this,"CHANNEL_ID")
//        builder.setContentIntent(contentIntent)
//            .setSmallIcon(android.R.drawable.alert_dark_frame)
//            .setContentTitle("Количество одногруппников в базе")
//            .setContentText(groupmateLen.toString())
//        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.notify(NOTIFY_ID, builder.build())
//
//    }


}