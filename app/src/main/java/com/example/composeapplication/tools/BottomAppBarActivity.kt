package com.example.composeapplication.bottomAppBar

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeapplication.checkerTaskList.CheckerTaskList
import com.example.composeapplication.ui.theme.ComposeApplicationTheme



class BottomAppBarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BottomAppBarUI()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomAppBarUI() {

    val bottomNavItems = listOf(
        BottomBarItem(
            icon = Icons.Filled.Home,
            title = "Home"
        ),
        BottomBarItem(
            icon = Icons.Filled.Person,
            title = "Profile"
        ),
        BottomBarItem(
            icon = Icons.Filled.Settings,
            title = "Setting"
        )
    )

    val context = LocalContext.current
    val selectedItem = remember { mutableStateOf(bottomNavItems[0]) }
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primary,
            ) {
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(item.title) },
                        selected = item == selectedItem.value,
                        onClick = {
                            selectedItem.value = item
                            when (selectedItem.value.title) {
                                "Home" -> {
                                    context.startActivity(
                                        Intent(
                                            context,
                                            CheckerTaskList::class.java
                                        )
                                    )

                                }

                                "Profile" -> {
                                    context.startActivity(
                                        Intent(
                                            context,
                                            CheckerTaskList::class.java
                                        )
                                    )

                                }

                                "Setting" -> {
                                    context.startActivity(
                                        Intent(
                                            context,
                                            CheckerTaskList::class.java
                                        )
                                    )

                                }

                            }

                        },
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.background(Color.Gray).fillMaxWidth().fillMaxHeight(),
        ) {
            Text(text = "BottomAppAbr", modifier = Modifier.fillMaxWidth().fillMaxHeight(), textAlign = TextAlign.Center,)
        }
    }
}
data class BottomBarItem(val icon: ImageVector, val title: String)


@Preview(showBackground = true)
@Composable
fun BottomAppBar() {
    ComposeApplicationTheme {
        BottomAppBarUI()
    }
}