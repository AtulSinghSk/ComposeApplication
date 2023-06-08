package com.example.composeapplication.splash

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
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class SplashScreenViewModel(app: Application, private val repository: AppRepository): AndroidViewModel(app) {




}