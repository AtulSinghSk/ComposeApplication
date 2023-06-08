package com.example.composeapplication.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.composeapplication.api.APIService
import com.example.composeapplication.api.RetrofitHelper
import com.example.composeapplication.db.AppDatabase


class MyApplication:Application(),LifecycleObserver {
    var mInstance: MyApplication? = null
    lateinit var apiService: APIService
    lateinit var appDatabase: AppDatabase
   companion object{
       lateinit var apConx:Context
   }

    @Synchronized
    fun getInstance(): MyApplication? {
        return mInstance
    }
    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this)
        apiService = RetrofitHelper.getInstance(applicationContext)
        appDatabase = AppDatabase.getDatabase(applicationContext)
        mInstance=this
        apConx=applicationContext



    }


}