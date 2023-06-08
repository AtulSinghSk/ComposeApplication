package com.example.composeapplication.logIn

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "LogIn")
class LogInDataModel : Serializable {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("Id")
    var Id: Int = 0

    @SerializedName("Mobile")
    var mobile: String = ""
    @SerializedName("Password")
    var password: String = ""

    constructor( mobile: String, password: String) {
        this.mobile = mobile
        this.password = password
    }

    override fun toString(): String {
        return "ColorDataModel(Id=$Id, mobile='$mobile', password='$password')"
    }

}
