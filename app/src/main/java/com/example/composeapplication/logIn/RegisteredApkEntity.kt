package com.example.composeapplication.logIn

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisteredApkEntity {
        @Expose
        @SerializedName("Password")
        var password: String? = null

        @Expose
        @SerializedName("UserName")
        var userName: String? = null

    }