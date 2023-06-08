package com.example.composeapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.composeapplication.logIn.LogInDataModel

@Database(entities = [LogInDataModel::class,],version =3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun AppDaoAccess(): AppDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private var DB_NAME = "LogInUser"
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context,
                        AppDatabase::class.java, DB_NAME
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}
