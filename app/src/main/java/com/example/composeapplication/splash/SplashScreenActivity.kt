package com.example.composeapplication.splash

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.composeapplication.R
import com.example.composeapplication.checkerTaskList.CheckerTaskList
import com.example.composeapplication.home.HomeActivity
import com.example.composeapplication.logIn.LogInActivity
import com.example.composeapplication.repository.AppRepository
import com.example.composeapplication.ui.theme.ComposeApplicationTheme
import kotlinx.coroutines.delay

class SplashScreenActivity : ComponentActivity() {

    lateinit var splashScreenViewModel: SplashScreenViewModel

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
                    splashScreenViewModel =
                        ViewModelProvider(
                            this,
                            SplashScreenViewModelFactory(
                                context.applicationContext as Application,
                                appRepository
                            )
                        ).get(
                            SplashScreenViewModel::class.java
                        )



                    Log.e("TAG", "onCreate1111: ", )
                    Token(splashScreenViewModel)
                    Splash()

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Splash() {
    ComposeApplicationTheme {
        Scaffold(
        ) { padding ->
            SplashScreen(Modifier.padding(padding))
        }
    }

}

// Step: Home screen - Scrolling
@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.splash_screen),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentScale = ContentScale.FillBounds
    )
    GoToMainScreen()
}

@Composable
fun GoToMainScreen() {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        delay(2000L)
        context.startActivity(Intent(context, HomeActivity::class.java))
    }
}

@Composable
fun Token(splashScreenViewModel: SplashScreenViewModel) {

    val context = LocalContext.current
    var username="DeliveryApp"
    var password="abcd@1234"

}


@Preview(showBackground = true, device = "id:pixel_xl")
@Composable
fun SplashScreenPreview() {
    ComposeApplicationTheme {
        Splash()
    }
}