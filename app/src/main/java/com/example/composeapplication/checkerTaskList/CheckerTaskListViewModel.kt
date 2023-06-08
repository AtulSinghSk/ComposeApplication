package com.example.composeapplication.checkerTaskList

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.composeapplication.R
import com.example.composeapplication.api.NetworkUtils
import com.example.composeapplication.api.Response
import com.example.composeapplication.checkerTaskList.CheckerPickerListPaging
import com.example.composeapplication.logIn.LogInDataModel
import com.example.composeapplication.model.TaskListModel
import com.example.composeapplication.model.TaskModelList
import com.example.composeapplication.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class CheckerTaskListViewModel(app: Application, private val repository: AppRepository, private val checkerPickerListPaging: CheckerPickerListPaging): AndroidViewModel(app) {
   fun insertData(model: LogInDataModel?) = viewModelScope.launch(Dispatchers.Main) {
        repository.insertData(model)
    }

    private val _uiState = MutableStateFlow<Response<TaskListModel>>(Response.Loading())
    val uiState: StateFlow<Response<TaskListModel>> = _uiState



    fun getCheckerPickerList(WarehouseId:Int,Skip:Int,Take:Int) = viewModelScope.launch (Dispatchers.IO) {
        if(NetworkUtils.isInternetAvailable(getApplication<Application>())) {
            _uiState.value= Response.Loading()
            val result = repository.getCheckerPickerList(WarehouseId, Skip, Take )
            if(result?.body() != null){
                _uiState.value= Response.Success(result.body())
                Log.e("TAG", "getCheckerPickerList222:${result.body()} " )
            }else{
                _uiState.value= Response.Error("Something went wrong")
            }
        } else{
            _uiState.value= Response.Error(getApplication<Application>().getString(R.string.internet_connection))
        }
    }


    val user: Flow<PagingData<TaskModelList>> = Pager(PagingConfig(pageSize = 10)) { checkerPickerListPaging }.flow.cachedIn(viewModelScope)


}