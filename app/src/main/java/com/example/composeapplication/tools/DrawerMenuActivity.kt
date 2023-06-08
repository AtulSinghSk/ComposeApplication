package com.example.composeapplication.tools

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapplication.R
import com.example.composeapplication.bottomAppBar.BottomAppBarActivity
import com.example.composeapplication.checkerTaskList.CheckerTaskList
import com.example.composeapplication.home.DrawerItem
import com.example.composeapplication.home.HomeActivity
import com.example.composeapplication.ui.theme.ComposeApplicationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DrawerMenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopAppBarUI()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopAppBarUI() {
    val context = LocalContext.current
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navigationIcon = painterResource(R.drawable.baseline_edit_note_24)


    val items = listOf(
        DrawerItem(
            icon = Icons.Filled.Home,
            title = "Home"
        ),
        DrawerItem(
            icon = Icons.Filled.Person,
            title = "TopAppBar"
        ),
        DrawerItem(
            icon = Icons.Filled.Settings,
            title = "BottomAppBar"
        ),

        DrawerItem(
            icon = Icons.Filled.Settings,
            title = "CheckerTaskList"
        )
    )
    val selectedItem = remember { mutableStateOf(items[0]) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.White,

                ) {
                Spacer(Modifier.height(12.dp))
                //  NavigationDrawer()

                items.forEach { item ->
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
                            }

                        },

                        shape = RectangleShape
                    )
                }
            }
        },
        content = {
            Scaffold(
            ) { padding -> DraweMenu(context,scope, drawerState)

            }

        },
        modifier = Modifier,
        scrimColor = Color.Transparent

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DraweMenu(context: Context,scope: CoroutineScope, drawerState: DrawerState) {
    androidx.compose.material3.TopAppBar(
        title = {
                Text(text = "Menu")
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
        actions = {
            IconButton(onClick = {
                Toast.makeText(context, "Notification Icon Click", Toast.LENGTH_SHORT).show()
            })
            {
                Icon(imageVector = Icons.Filled.Notifications, contentDescription = "Navigation icon")
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun TopAppBar1(backgroundColor: Any?, function: () -> Unit) {
    ComposeApplicationTheme {
        TopAppBarUI()
    }
}