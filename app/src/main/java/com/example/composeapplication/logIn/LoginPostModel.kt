package com.example.composeapplication.logIn

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class LoginPostModel : Serializable {


    @Expose
    @SerializedName("Mobile")
    var Mobile: String? = null

    @Expose
    @SerializedName("password")
    var password: String? = null

    @Expose
    @SerializedName("fcmId")
    var fcmId: String? = null

    @Expose
    @SerializedName("deviceId")
    var deviceId: String? = null

    constructor(Mobile: String?, password: String?, fcmId: String?, deviceId: String?) {
        this.Mobile = Mobile
        this.password = password
        this.fcmId = fcmId
        this.deviceId = deviceId
    }
}