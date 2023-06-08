package com.example.composeapplication.checkerTaskList

import android.util.Log
import androidx.compose.ui.focus.FocusDirection.Companion.In
import androidx.paging.LoadType
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composeapplication.model.TaskModelList
import com.example.composeapplication.repository.AppRepository
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException
import java.util.EmptyStackException

class CheckerPickerListPaging(private val repository: AppRepository, private var WarehouseId: Int,private var Take: Int):
    PagingSource<Int, TaskModelList>() {
    var emptyList11=false
    override fun getRefreshKey(state: PagingState<Int, TaskModelList>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
}
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TaskModelList> {
        return try {
            val skip = params.key ?: 0
            val userList = repository.getCheckerPickerList(WarehouseId,skip,Take)
            delay(2000)
            LoadResult.Page(
                data = userList.body()!!.taskList!!,
                prevKey = null,
                nextKey = if (userList.body()!!.taskList!!.isEmpty()){
                    null
                } else {
                    skip + 10
                },

            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)

        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}
