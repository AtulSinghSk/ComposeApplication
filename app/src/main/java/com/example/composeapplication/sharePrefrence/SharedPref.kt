package com.example.composeapplication.sharePrefrence

import android.content.Context
import android.content.SharedPreferences



class SharedPref(var appContext: Context) {
    private val sharedPreferences: SharedPreferences


    init {
        sharedPreferences = appContext.getSharedPreferences(
            SHARED_PREFERENCE, 0
        ) //PreferenceManager.getDefaultSharedPreferences(context);

    }

    fun putString(key: String?, `val`: String?) {
        sharedPreferences.edit().putString(key, `val`).apply()
    }

    fun getString(key: String): String {
        return sharedPreferences.getString(key, "")!!
    }

    fun putInt(key: String?, `val`: Int?) {
        sharedPreferences.edit().putInt(key, `val`!!).apply()
    }

    fun putBoolean(key: String?, `val`: Boolean?) {
        sharedPreferences.edit().putBoolean(key, `val`!!).apply()
    }

    fun getBoolean(key: String?): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun getInt(key: String?): Int {
        return sharedPreferences.getInt(key, 0)
    }

    // for username string preferences
    fun setStringSharedPreference(name: String?, value: String?) {
        val settings = appContext.getSharedPreferences(SHARED_PREFERENCE, 0)
        val editor = settings.edit()
        editor.putString(name, value)
        editor.apply()
    }

    companion object {

        private var instance: SharedPref? = null
        var SHARED_PREFERENCE = "Compose"
        var LOGIN= "LogIn"
        var  TOKEN_NAME = "token_name"
        var TOKEN_PASSWORD = "token_pass"
        var TOKEN = "empty_token"
        var PEOPLEID = "people_id"
        @JvmStatic
        fun getInstance(ctx: Context): SharedPref {
            if (instance == null) {
                instance = SharedPref(ctx)
            }
            return instance!!
        }

    }
}