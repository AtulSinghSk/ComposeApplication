package com.example.composeapplication.db

import androidx.room.Dao
import androidx.room.Insert
import com.example.composeapplication.logIn.LogInDataModel


@Dao
interface AppDao {
    @Insert
    fun insertCustData(model: LogInDataModel?)


}
