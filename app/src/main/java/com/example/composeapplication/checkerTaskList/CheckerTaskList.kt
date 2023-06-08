package com.example.composeapplication.checkerTaskList

import android.app.Application
import android.content.Context

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.paging.PagingSource
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.composeapplication.R
import com.example.composeapplication.sharePrefrence.SharedPref.Companion.LOGIN
import com.example.composeapplication.repository.AppRepository
import com.example.composeapplication.model.TaskModelList
import com.example.composeapplication.sharePrefrence.SharedPref
import com.example.composeapplication.ui.theme.ComposeApplicationTheme
import com.example.composeapplication.utils.ErrorView
import com.example.composeapplication.utils.HttpStatusCode
import com.example.composeapplication.utils.HttpStatusCode.getHttpCodeFromLoadError

import kotlin.math.roundToInt

class CheckerTaskList : ComponentActivity() {
    companion object {
        lateinit var homePageViewModel: CheckerTaskListViewModel
    }

    var WarehouseId = 213
    var Take = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current

                    val appRepository = AppRepository(context.applicationContext as Application)
                    val checkerPickerListPaging =
                        CheckerPickerListPaging(appRepository, WarehouseId, Take)
                    homePageViewModel =
                        ViewModelProvider(
                            this,
                            CheckerTaskListViewModelFactory(
                                context.applicationContext as Application,
                                appRepository,
                                checkerPickerListPaging
                            )
                        ).get(
                            CheckerTaskListViewModel::class.java
                        )
                    SharedPref.getInstance(context).putBoolean(LOGIN, true)
                    var login = SharedPref.getInstance(context).getBoolean(LOGIN)


                    UserList(viewModel = homePageViewModel, context = this)

                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Ui(data: TaskModelList) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp)

        ) {
            Text(text = "PickerNo : " + data.OrderPickerMasterId)

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
            ) {
                Text(
                    text = "40:22:39",
                    Modifier.weight(1f),
                    color = colorResource(R.color.light_red)
                )

                Text(
                    text = "No. of Item : " + data.NoOfItems,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End
                )

            }
        }




        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(1f)
                ) {

                    Text(text = "Created By", color = colorResource(R.color.light_gray))
                    Text(text = data.CreatedBy.toString())
                    Text(text = "Pickerperson", color = colorResource(R.color.light_gray))
                    Text(text = data.Pickerperson.toString())
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .weight(0.1f),

                    ) {
                    Image(
                        painter = painterResource(R.drawable.vertical_line_image_view),
                        contentDescription = "Content description for visually impaired",
                        modifier = Modifier.height(80.dp),
                    )

                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {


                    Text(text = data.TotalAmount.roundToInt().toString())
                    Text(text = "Amount")
                }

            }

        }

        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(colorResource(R.color.dark_yellow)),
            elevation = CardDefaults.cardElevation(10.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentHeight()

                .clickable(onClick = {
                }),


            ) {
            Text(
                text = "In Progress",
                color = colorResource(R.color.white),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)

            )
        }


    }


}


@Composable
fun UserList(modifier: Modifier = Modifier, viewModel: CheckerTaskListViewModel, context: Context) {
    val lazyRecipesItems = viewModel.user.collectAsLazyPagingItems()
    val listState: LazyListState = rememberLazyListState()

    val isScrollToEnd by remember {
        derivedStateOf {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == listState.layoutInfo.totalItemsCount - 1
        }
    }

    LazyColumn {
            items(lazyRecipesItems.itemCount) { index ->
                lazyRecipesItems[index]?.let {
                    Ui(it)
                    Log.e("TAG", "Atulssd ${it.emptyList}: ", )


                }
            }
        lazyRecipesItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    Log.e("TAG," ,"UserList1" )

                    //  item { LoadingView() }
                    item {
                        Column(
                            modifier = Modifier
                                .fillParentMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(8.dp),
                                text = "Refresh Loading"
                            )

                            CircularProgressIndicator(color = Color.Black)
                            Log.e("TAG," ,"UserList2" )
                        }
                    }
                }
                loadState.append is LoadState.NotLoading -> {
                    if (lazyRecipesItems.itemCount == 0 && loadState.append.endOfPaginationReached) {
                        // Pagination has reached the end of the data set and the list is empty
                        Log.e("TAG,", "endOfPaginationReached: ", )
                    } else if (lazyRecipesItems.itemCount == 0) {
                        // Pagination has not reached the end, but the list is currently empty
                        Log.e("TAG,", "endOfPaginationReached:1 ", )
                    } else {
                        // Pagination is idle and there are more items to load
                        Log.e("TAG,", "endOfPaginationReached:2 ", )
                    }
                }

                loadState.refresh is LoadState.Error -> {

                    val e = lazyRecipesItems.loadState.refresh as LoadState.Error
                    //   item { LoadingView()  }
                    Log.e("TAG," ,"UserList3" )

                    item {
                        ErrorView(message = HttpStatusCode.getMeaningfulMessage(
                            getHttpCodeFromLoadError(e.error.message.toString()),
                            LocalContext.current
                        ), onClickRetry = { retry() })
                        Log.e("TAG," ,"UserList4" )
                    }
                }

                loadState.append is LoadState.Loading -> {
                    //item { LoadingView()  }
                    Log.e("TAG," ,"UserList5 ${item{ }}" )
                    item {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(text = "Pagination Loading")
                            CircularProgressIndicator(color = Color.Black)



                            Log.e("TAG,", "endOfPaginationReached3333: ${loadState.append.endOfPaginationReached}", )

                        }


                    }
                }



                loadState.append is LoadState.Error -> {
                    Log.e("TAG," ,"UserList7" )
                    val e = lazyRecipesItems.loadState.append as LoadState.Error
                    item {
                        ErrorView(message = HttpStatusCode.getMeaningfulMessage(
                            getHttpCodeFromLoadError(e.error.localizedMessage!!),
                            LocalContext.current
                        ), onClickRetry = { retry() })
                        Log.e("TAG," ,"UserList8" )


                    }
                }


            }

        }


    }



}



@Preview(showBackground = true, device = "id:pixel_xl")
@Composable
fun ScreenContentPreview() {
    //ComposeApplicationTheme { MyScreen() }
    // ComposeApplicationTheme { SimpleListView(homePageViewModel) }
    //ComposeApplicationTheme { Ui() }

}
