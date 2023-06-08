package com.example.composeapplication.logIn

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CustomerResponse : Serializable {

    @Expose
    @SerializedName("Message")
     var Message: String? = null

    @Expose
    @SerializedName("Status")
    var Status = false

    @Expose
    @SerializedName("P")
    var P: PEntity? = null

}