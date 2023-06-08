package com.example.composeapplication.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class OtpVerifyResponse: Serializable {
     @SerializedName("Status")
     var status: Boolean = false
     @SerializedName("Message")
     val message: String=""
     @SerializedName("P")
     val peopleModel: PeopleModel? = null
 }