package com.example.composeapplication.checkerTaskList

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composeapplication.repository.AppRepository

class CheckerTaskListViewModelFactory(val app: Application, private val repository: AppRepository, private val checkerPickerListPaging: CheckerPickerListPaging) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CheckerTaskListViewModel(app,repository,checkerPickerListPaging) as T
    }
}