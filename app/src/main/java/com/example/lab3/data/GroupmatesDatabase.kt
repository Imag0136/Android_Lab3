package com.example.lab3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [Groupmates::class], version = 1, exportSchema = false)
abstract class GroupmatesDatabase: RoomDatabase() {
    abstract fun groupmatesDAO():GroupmatesDAO

//    companion object{
//        @Volatile
//        private var INSTANCE: GroupmatesDatabase? = null
//
//        fun getDatabase(context: Context): GroupmatesDatabase{
//            val tempInstance = INSTANCE
//            if(tempInstance != null){
//                return tempInstance
//            }
//            synchronized(this){
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    GroupmatesDatabase::class.java,
//                    "groupamates_database"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//
//        }
//    }
}