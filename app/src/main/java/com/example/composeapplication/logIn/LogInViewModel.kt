package com.example.composeapplication.logIn

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.composeapplication.R
import com.example.composeapplication.api.AppVersionModel
import com.example.composeapplication.api.NetworkUtils
import com.example.composeapplication.api.PeopleModel
import com.example.composeapplication.api.Response
import com.example.composeapplication.logIn.LogInDataModel
import com.example.composeapplication.model.TaskListModel
import com.example.composeapplication.repository.AppRepository
import com.example.composeapplication.repository.SingleLiveEvent
import com.example.composeapplication.splash.TokenResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LogInViewModel(app: Application, private val repository: AppRepository): AndroidViewModel(app) {


    private val _LoginUiState = MutableStateFlow<Response<CustomerResponse>>(Response.Loading())
    val LoginUiState: StateFlow<Response<CustomerResponse>> = _LoginUiState

    private val _tokenUiState = MutableStateFlow<Response<TokenResponse>>(Response.Loading())
    val tokenuiState: StateFlow<Response<TokenResponse>> = _tokenUiState


    fun loginMobile( loginPostModel:LoginPostModel ) = viewModelScope.launch (Dispatchers.IO) {
        if(NetworkUtils.isInternetAvailable(getApplication<Application>())) {
            _LoginUiState.value= Response.Loading()
            val result = repository.loginMobile(loginPostModel)
            if(result?.body() != null){
                _LoginUiState.value= Response.Success(result.body())
                Log.e("TAG", "getCheckerPickerList222:${result.body()} " )
            }else{
                _LoginUiState.value= Response.Error("Something went wrong")
            }
        } else{
            _LoginUiState.value= Response.Error(getApplication<Application>().getString(R.string.internet_connection))
        }
    }

    fun getToken(grant_type: String,username: String, password: String) = viewModelScope.launch (Dispatchers.IO) {
        if(NetworkUtils.isInternetAvailable(getApplication<Application>())) {
            _tokenUiState.value= Response.Loading()
            val result = repository.getToken(grant_type, username, password )
            if(result?.body() != null){
                _tokenUiState.value= Response.Success(result.body())
                Log.e("TAG", "getCheckerPickerList222:${result.body()} " )
            }else{
                _tokenUiState.value= Response.Error("Something went wrong")
            }
        } else{
            _tokenUiState.value= Response.Error(getApplication<Application>().getString(R.string.internet_connection))
        }
    }
}