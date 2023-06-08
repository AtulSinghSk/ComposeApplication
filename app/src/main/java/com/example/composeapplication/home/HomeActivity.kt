package com.example.composeapplication.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapplication.R
import com.example.composeapplication.bottomAppBar.BottomAppBarActivity
import com.example.composeapplication.checkerTaskList.CheckerTaskList
import com.example.composeapplication.home.ui.theme.ComposeApplicationTheme
import com.example.composeapplication.tools.DialogBoxActivity
import com.example.composeapplication.tools.DrawerMenuActivity
import com.example.composeapplication.tools.TabLayoutActivity
import com.example.composeapplication.tools.TabLayoutWithViewPagerActivity
import com.example.composeapplication.tools.ViewPagerActivity
import com.example.composeapplication.utils.HomeScreen
import kotlinx.coroutines.launch

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyScreen()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen() {
    val context = LocalContext.current
    ComposeApplicationTheme {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val navigationIcon = painterResource(R.drawable.baseline_edit_note_24)
        // val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)

        val drawerItems = listOf(
            DrawerItem(
                icon = Icons.Filled.Home,
                title = "Home"
            ),
            DrawerItem(
                icon = Icons.Filled.Edit,
                title = "TopAppBar"
            ),
            DrawerItem(
                icon = Icons.Filled.Edit,
                title = "BottomAppBar"
            ),

            DrawerItem(
                icon = Icons.Filled.Edit,
                title = "CheckerTaskList"
            ),
            DrawerItem(
                icon = Icons.Filled.Edit,
                title = "TabLayout"
            ),
            DrawerItem(
                icon = Icons.Filled.Edit,
                title = "ViewPager"
            ),
            DrawerItem(
                icon = Icons.Filled.Edit,
                title = "TabLayOutWithViewPager"
            ),
            DrawerItem(
                icon = Icons.Filled.Edit,
                title = "DialogBox"
            )
        )
        val bottomNavItems = listOf("Home", "Create", "Settings")

        val selectedItem = remember { mutableStateOf(drawerItems[0]) }
        Log.e("TAG", "GoToPage3333:$selectedItem ")
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    drawerContainerColor = Color.White,

                    ) {
                    Spacer(Modifier.height(12.dp))
                    //  NavigationDrawer()

                    drawerItems.forEach { item ->
                        NavigationDrawerItem(
                            icon = { Icon(item.icon, contentDescription = null) },
                            label = { Text(item.title) },
                            selected = item == selectedItem.value,
                            onClick = {
                                scope.launch { drawerState.close() }
                                selectedItem.value = item
                                when (selectedItem.value.title) {
                                    "Home" -> {
                                        context.startActivity(
                                            Intent(
                                                context,
                                                HomeActivity::class.java
                                            )
                                        )

                                    }
                                    "TopAppBar" -> {
                                        context.startActivity(
                                            Intent(
                                                context,
                                                DrawerMenuActivity::class.java
                                            )
                                        )

                                    }
                                    "BottomAppBar" -> {
                                        context.startActivity(
                                            Intent(
                                                context,
                                                BottomAppBarActivity::class.java
                                            )
                                        )

                                    }
                                    "CheckerTaskList" -> {
                                        context.startActivity(
                                            Intent(
                                                context,
                                                CheckerTaskList::class.java
                                            )
                                        )

                                    }
                                    "TabLayout" -> {
                                        context.startActivity(
                                            Intent(
                                                context,
                                                TabLayoutActivity::class.java
                                            )
                                        )

                                    }
                                    "ViewPager" -> {
                                        context.startActivity(
                                            Intent(
                                                context,
                                                ViewPagerActivity::class.java
                                            )
                                        )

                                    }
                                    "TabLayOutWithViewPager" -> {
                                        context.startActivity(
                                            Intent(
                                                context,
                                                TabLayoutWithViewPagerActivity::class.java
                                            )
                                        )

                                    }
                                    "DialogBox" -> {
                                        context.startActivity(
                                            Intent(
                                                context,
                                                DialogBoxActivity::class.java
                                            )
                                        )

                                    }
                                }

                            },

                            shape = RectangleShape
                        )
                    }
                }
            },
            content = {
                Scaffold(
                    bottomBar = {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.primary,
                        ) {
                            bottomNavItems.forEach { item ->
                                NavigationBarItem(
                                    onClick = {
                                        Toast.makeText(context, "${item}", Toast.LENGTH_SHORT)
                                            .show()
                                    },
                                    label = {
                                        Text(
                                            text = item,
                                            fontWeight = FontWeight.SemiBold,
                                        )
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Favorite,
                                            contentDescription = "${item} Icon",
                                        )
                                    },
                                    selected = true
                                )
                            }
                        }
                    }
                ) { padding ->
                    HomeScreen(context, Modifier.padding(padding), scope, drawerState)

                }

            },
            modifier = Modifier,
            scrimColor = Color.Transparent

        )
    }
}



@Composable
fun NavigationDrawerItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = icon,
                contentDescription = title
            )
        }
        Text(text = title)
    }
}

@Composable
fun NavigationDrawer() {
    Column {
        NavigationDrawerItem(
            icon = Icons.Filled.Home,
            title = "Home",
            onClick = { /* Handle home item click */ }
        )
        NavigationDrawerItem(
            icon = Icons.Filled.Home,
            title = "CheckerTaskList",
            onClick = { /* Handle home item click */ }
        )
    }

}

/*@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.contentDescription
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.title,
                    style = itemTextStyle,
                    modifier = Modifier.weight(1f)
                )
            }
        }

    }

}*/



data class DrawerItem(val icon: ImageVector, val title: String)


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ComposeApplicationTheme {
        MyScreen()
    }
}