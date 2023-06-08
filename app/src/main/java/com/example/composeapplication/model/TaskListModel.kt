package com.example.composeapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TaskListModel {

    @Expose
    @SerializedName("Message")
    var Message: String? = null

    @Expose
    @SerializedName("Status")
    var Status: Boolean = false


    @Expose
    @SerializedName("Data")
    var taskList: ArrayList<TaskModelList>? = null


}