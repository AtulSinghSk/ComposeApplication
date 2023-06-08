package com.example.composeapplication.logIn

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PEntity {
        @Expose
        @SerializedName("Roles")
        var roles: RolesEntity? = null

        @Expose
        @SerializedName("RegisteredApk")
        var registeredApk: RegisteredApkEntity? = null

        @Expose
        @SerializedName("FcmId")
        var fcmId: String? = null

        @Expose
        @SerializedName("DeviceId")
        var deviceId: String? = null

        @Expose
        @SerializedName("UpdatedDate")
        var updatedDate: String? = null

        @Expose
        @SerializedName("CreatedDate")
        var createdDate: String? = null

        @Expose
        @SerializedName("VehicleCapacity")
        var vehicleCapacity = 0

        @Expose
        @SerializedName("VehicleId")
        var vehicleId = 0

        @Expose
        @SerializedName("Password")
        var password: String? = null

        @Expose
        @SerializedName("Mobile")
        var mobile: String? = null

        @Expose
        @SerializedName("DisplayName")
        var displayName: String? = null

        @Expose
        @SerializedName("Email")
        var email: String? = null

        @Expose
        @SerializedName("PeopleLastName")
        var peopleLastName: String? = null

        @Expose
        @SerializedName("PeopleFirstName")
        var peopleFirstName: String? = null

        @Expose
        @SerializedName("WarehouseId")
        var warehouseId = 0

        @Expose
        @SerializedName("CompanyId")
        var companyId = 0

        @Expose
        @SerializedName("PeopleID")
        var peopleID = 0

    }