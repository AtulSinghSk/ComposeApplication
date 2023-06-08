package com.example.composeapplication.logIn

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RolesEntity {
        @Expose
        @SerializedName("RoleList")
        var roleList: ArrayList<RoleListEntity>? = null

    }