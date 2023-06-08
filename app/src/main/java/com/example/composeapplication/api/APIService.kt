package com.example.composeapplication.api

import com.example.composeapplication.logIn.CustomerResponse
import com.example.composeapplication.logIn.LoginPostModel
import com.example.composeapplication.model.TaskListModel
import com.example.composeapplication.splash.TokenResponse
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface APIService {

    @POST("api/Sarthi/login")
    suspend  fun loginMobile(@Body loginPostModel: LoginPostModel): Response<CustomerResponse>

    @FormUrlEncoded
    @POST("/token")
   suspend fun getToken(
        @Field("grant_type") grant_type: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<TokenResponse>


    @GET("api/Picker/GetChekerTaskList/{WarehouseId}/{Skip}/{Take}")
   suspend fun getCheckerPickerList(
        @Path("WarehouseId") WarehouseId: Int, @Path("Skip") Skip: Int, @Path("Take") Take: Int
    ): Response<TaskListModel>

}