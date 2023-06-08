package com.example.composeapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TaskModelList {

    @Expose
    @SerializedName("CreatedDate")
    var CreatedDate: String? = null

    @Expose
    @SerializedName("ModifiedDate")
    var ModifiedDate: String? = null

    @Expose
    @SerializedName("DBoyName")
    var DBoyName: String? = null

    @Expose
    @SerializedName("Pickerperson")
    var Pickerperson: String? = null

    @Expose
    @SerializedName("OrderPickerMasterId")
    var OrderPickerMasterId: Int = 0

    @Expose
    @SerializedName("TotalAmount")
    var TotalAmount: Double = 0.0

    @Expose
    @SerializedName("NoOfItems")
    var NoOfItems: Int = 0

    @Expose
    @SerializedName("CreatedBy")
    var CreatedBy: String? = null


    var emptyList=false

    constructor(

        emptyList:Boolean
    ) {
        this.emptyList=emptyList
    }
}