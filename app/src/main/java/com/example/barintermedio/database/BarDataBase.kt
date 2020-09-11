package com.example.barintermedio.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATA_BASE_NAME = "Bar_db"

@Database(entities = [Bar::class], version = 1)
abstract  class BarDataBase : RoomDatabase(){

    abstract  fun getBarDao(): BarDao

    companion object{
        @Volatile
        private var INSTANCE: BarDataBase? = null

        fun getDatabase(context: Context): BarDataBase{
            val tempInstances = INSTANCE
            if (tempInstances != null){
                return tempInstances
            }

            synchronized(this){
                val instance = Room.databaseBuilder(context, BarDataBase::class.java, DATA_BASE_NAME).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}