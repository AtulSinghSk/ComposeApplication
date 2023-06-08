package com.example.composeapplication.utils

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapplication.R
import com.example.composeapplication.home.HomeActivity
import com.example.composeapplication.model.TaskModelList
import com.example.composeapplication.sharePrefrence.SharedPref
import com.example.composeapplication.ui.theme.ComposeApplicationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    var context: Context? = null
    constructor(context: Context?) {
        this.context = context
        setContext1(context)
    }
    companion object {
        var serverFormat = "yyyy-MM-dd'T'HH:mm:ss"


        private val serverdateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

        var context: Context? = null
        fun setContext1(context: Context?) {
            Companion.context = context
        }

        fun currentDay(): String {
            val sdf = SimpleDateFormat("EEEE", Locale.ENGLISH)
            val c = Date()
            val dayOfTheWeek: String = sdf.format(c)
            return dayOfTheWeek
        }

        fun isNullOrEmpty(s: String?): Boolean {
            return s == null || s.length == 0 || s.equals(
                "null"
            ) || s.equals("0")
        }

        fun getDeviceUniqueID(context: Context): String? {
            return Settings.Secure.getString(context!!.contentResolver, Settings.Secure.ANDROID_ID)
        }


        fun getToken(context: Context): String {
            return SharedPref.getInstance(context).getString(SharedPref.TOKEN)
        }

        fun getPeopleId(context: Context): String {
            return SharedPref.getInstance(context).getString(SharedPref.PEOPLEID)
        }




        fun getDateFormat(serverDate: String?, requiredFormat: String?): String? {
            return if (!isNullOrEmpty(serverDate)) {
                val originalFormat: DateFormat = SimpleDateFormat(serverFormat, Locale.ENGLISH)
                originalFormat.timeZone = TimeZone.getDefault()
                val targetFormat: DateFormat = SimpleDateFormat(requiredFormat, Locale.ENGLISH)
                var date: Date? = null
                var formattedDate: String? = ""
                try {
                    date = originalFormat.parse(serverDate)
                    formattedDate = targetFormat.format(date)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
                formattedDate
            } else {
                "null"
            }
        }


        fun hideKeyboard(activity: Activity) {
            try {
                val inputManager =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(
                    activity.currentFocus!!.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }


        fun scaleBitmap(bitmap: Bitmap, newWidth: Int, newHeight: Int, skcode: String?): Bitmap? {
            val scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888)
            val scaleX = newWidth / bitmap.width.toFloat()
            val scaleY = newHeight / bitmap.height.toFloat()
            val pivotX = 0f
            val pivotY = 0f
            val scaleMatrix = Matrix()
            scaleMatrix.setScale(scaleX, scaleY, pivotX, pivotY)
            val canvas = Canvas(scaledBitmap)
            canvas.setMatrix(scaleMatrix)
            canvas.drawBitmap(bitmap, 0f, 0f, Paint(Paint.FILTER_BITMAP_FLAG))
            val color = Paint()
            color.textSize = 25f
            color.color = Color.WHITE
            canvas.drawText(skcode!!, 30f, 40f, color)
            return scaledBitmap
        }


    }

    fun isNullOrEmpty(s: String?): Boolean {
        return s == null || s.length == 0 || s.equals("null", ignoreCase = true) || s.equals(
            "0",
            ignoreCase = true
        )
    }

    fun <T> isNullOrEmpty(a: ArrayList<T>?): Boolean {
        return a == null || a.size == 0
    }

    fun <T, Y> isNullOrEmpty(m: Map<T, Y>?): Boolean {
        return m == null || m.size == 0
    }
}

@Composable
fun LoadingView() {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight() ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator()
    }
}



@Composable
fun ErrorView(
    message: String,
    onClickRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = message,
            maxLines = 2,
            color = androidx.compose.ui.graphics.Color.Red,
            fontSize = 17.sp,
            textAlign = TextAlign.Center
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = stringResource(R.string.error_retry_button_text))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomAppBar1() {
    BottomAppBar(
        containerColor = androidx.compose.ui.graphics.Color.Gray,
        actions = {

            IconButton(onClick = { /* Check onClick */ }) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "",
                    tint = androidx.compose.ui.graphics.Color.Red,
                    modifier = Modifier.size(36.dp)
                )
            }
            IconButton(onClick = { /* Edit onClick */ }) {
                Icon(
                    Icons.Filled.Edit, contentDescription = "", tint = androidx.compose.ui.graphics.Color.Red
                )
            }
            IconButton(onClick = { /* Delete onClick */ }) {
                Icon(Icons.Filled.Delete, contentDescription = "", tint = androidx.compose.ui.graphics.Color.Red)
            }

        },
        contentColor = ListItemDefaults.contentColor
    )
}

// Step: Search bar - Modifiers
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(),
        placeholder = {
            Text("Search")
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

// Step: Align your body - Alignment
@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.paddingFromBaseline(
                top = 24.dp, bottom = 8.dp
            )
        )
    }
}



// Step: Favorite collection card - Material Surface
@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(192.dp)
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}




// Step: Home section - Slot APIs
@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(title).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}


// Step: Home screen - Scrolling
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    context: Context,
    modifier: Modifier = Modifier,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
    ) {
        Drawer(context, scope, drawerState)
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(onNavigationIconClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Scaffold Example"
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onNavigationIconClick()
            }) {
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = "navigation"
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer(context: Context, scope: CoroutineScope, drawerState: DrawerState) {
    TopAppBar(
        title = {
            Text(text = "SemicolonSpace")
        },

        navigationIcon = {
            IconButton(onClick = {
                scope.launch { drawerState.open() }
                Toast.makeText(context, "Navigation Icon Click", Toast.LENGTH_SHORT).show()
            })
            {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Navigation icon")
            }
        },
    )
}




@Composable
fun SimpleListView() {


   /* LaunchedEffect(Unit, block = {
        HomeActivity.homePageViewModel.getCheckerPickerList(213, 0, 10)
    })
    val ressult = HomeActivity.homePageViewModel.uiState.collectAsState().value
*/

    /*when (ressult) {
        is Response.Loading -> {
            //  isLodingAndTryBtn(1)
        }

        is Response.Success -> {
            ressult.data?.let {
                it.taskList?.let { it1 ->
                    LazyColumn1(it.taskList!!)



                }
            }
        }

        is Response.Error -> {
            // Toast.makeText(this, it.errorMesssage.toString(), Toast.LENGTH_SHORT).show()
            //  callLogin()
        }
    }*/

}

@Composable
fun LazyColumn1(taskList: ArrayList<TaskModelList>?) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    )
    {
        items(taskList!!) { data ->
            /* Ui(data)*/
        }
    }
}


@Preview(showBackground = true, device = "id:pixel_xl")
@Composable
fun ScreenContentPreview() {
   // ComposeApplicationTheme { MyScreen() }
    // ComposeApplicationTheme { SimpleListView(homePageViewModel) }
    //ComposeApplicationTheme { Ui() }


}
