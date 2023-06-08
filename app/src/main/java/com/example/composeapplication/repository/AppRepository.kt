package com.example.composeapplication.repository

import android.content.Context
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composeapplication.logIn.LogInDataModel
import com.example.composeapplication.logIn.LoginPostModel
import com.example.composeapplication.model.TaskListModel
import com.example.composeapplication.model.TaskModelList
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

class AppRepository(applicationContext: Context) {
    var apiService = (applicationContext as MyApplication).apiService
    var appdatabase = (applicationContext as MyApplication).appDatabase

    fun insertData(model: LogInDataModel?) = appdatabase.AppDaoAccess().insertCustData(model)
    suspend fun loginMobile(loginPostModel: LoginPostModel) = apiService.loginMobile(loginPostModel)
    suspend fun getToken(grant_type: String, username: String, password: String) =
        apiService.getToken(grant_type, username, password)

    suspend fun getCheckerPickerList(WarehouseId: Int, Skip: Int, Take: Int) =
        apiService.getCheckerPickerList(WarehouseId, Skip, Take)

}
