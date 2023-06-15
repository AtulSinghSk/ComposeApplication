package com.example.composeapplication.logIn

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.composeapplication.sharePrefrence.SharedPref
import com.example.composeapplication.sharePrefrence.SharedPref.Companion.PEOPLEID
import com.example.composeapplication.sharePrefrence.SharedPref.Companion.TOKEN
import com.example.composeapplication.sharePrefrence.SharedPref.Companion.TOKEN_NAME
import com.example.composeapplication.sharePrefrence.SharedPref.Companion.TOKEN_PASSWORD
import com.example.composeapplication.api.Response
import com.example.composeapplication.home.HomeActivity
import com.example.composeapplication.logIn.LogInActivity.Companion.logInViewModel

import com.example.composeapplication.repository.AppRepository
import com.example.composeapplication.ui.theme.ComposeApplicationTheme


class LogInActivity : ComponentActivity() {
    companion object{
        lateinit var logInViewModel: LogInViewModel
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val context = LocalContext.current
                    val appRepository = AppRepository(context.applicationContext as Application)
                    logInViewModel =
                        ViewModelProvider(
                            this,
                            LogInViewModelFactory(
                                context.applicationContext as Application,
                                appRepository
                            )
                        ).get(
                            LogInViewModel::class.java
                        )

                    SignInUi()

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInUi() {
    ComposeApplicationTheme {
        Scaffold(

        ) { padding ->
            LogIn(Modifier.padding())
        }
    }
}
@Composable
fun LogIn(padding: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
        CardView()
    }


}

@Composable
fun CardView(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {

        RowColum()

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowColum(){
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var mobileNumber by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current

    var mobile="5555566669"
    var password1="Sk@123"
    var IMEI=""
    var fcmToken="d8y00DRYTvS4s_CEGDrztR:APA91bEAD1QDyl0oe3T8NDMtwv-E5uNWQ6Fv7gkjGNwtBV4I3NiFxHwHbNTDAp54VqQAGr3OnqPFRDwfF8orzPi9Z5Ow-kFZGTFgLk8cs6AGOTJS94U_Hl49rp3zprT6pJaJ_fSPC8Ay"
    IMEI =
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    CallLoginApi(context,mobile,password1,fcmToken,IMEI)
    CallTockenApi(context)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            OutlinedTextField(
                value = mobileNumber,
                label = { Text(text = "Mobile Number") },
                onValueChange = {
                    mobileNumber = it
                    mobile=it.toString()
                },

                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                modifier = Modifier
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Search
                )
            )


            OutlinedTextField(
                value = password,
                label = { Text(text = "Password") },
                onValueChange = {
                    password = it
                    password1=it.toString()

                },
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                modifier = Modifier
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Search
                )
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {

                Button(
                    onClick = {


                        //your onclick code here

                        if( validation(context,mobileNumber,password)) {
                            Log.e("TAG", "lotgIn Success: ", )
                            context.startActivity(Intent(context, HomeActivity::class.java))
                        }
                    }, modifier = Modifier.fillMaxWidth(),
                    shape = CircleShape
                ) {
                    Text(text = "SignIn", fontSize = 18.sp)

                }
            }
        }

    }

}

fun validation(context: Context, mobileNumber: TextFieldValue, password: TextFieldValue):Boolean{
    if(mobileNumber.text.isNullOrEmpty()){
        Toast.makeText(context, "Mobile Number is required..!!", Toast.LENGTH_SHORT).show()
        return false
    }
    if (password.text.isNullOrEmpty()){
        Toast.makeText(context, "Password is required..!!", Toast.LENGTH_SHORT).show()
        return false
    }
    return true
}



@Composable
fun CallLoginApi(
    context: Context,
    mobile: String,
    password1: String,
    fcmToken: String,
    IMEI: String
) {

    LaunchedEffect(Unit, block = {
        Log.e("TAG", "CallTockenApi: 111111111", )
        val loginPostModel = LoginPostModel(mobile, password1, fcmToken, IMEI)
        logInViewModel.loginMobile(loginPostModel)
    })

    val  ressult = logInViewModel.LoginUiState .collectAsState().value

    when (ressult) {
        is Response.Loading -> {
            //  isLodingAndTryBtn(1)
        }
        is Response.Success -> {
            ressult.data?.let {
                var rApk= it.P!!.registeredApk!!
                var userName= rApk.userName
                var password= rApk.password
                var peopleId=it!!.P!!.peopleID.toString()

                SharedPref.getInstance(context).putString(TOKEN_NAME,userName)
                SharedPref.getInstance(context).putString(TOKEN_PASSWORD,password)
                SharedPref.getInstance(context).putString(PEOPLEID,peopleId)
            }
        }
        is Response.Error -> {
            // Toast.makeText(this, it.errorMesssage.toString(), Toast.LENGTH_SHORT).show()
            //  callLogin()
        }
    }
}

@Composable
fun CallTockenApi(context: Context) {
    var username= SharedPref.getInstance(context).getString(TOKEN_NAME)
    var password= SharedPref.getInstance(context).getString(TOKEN_PASSWORD)
    LaunchedEffect(Unit, block = {
        Log.e("TAG", "CallTockenApi: 22222222", )
        logInViewModel.getToken("password", username, password)
    })

    val  ressult = logInViewModel.tokenuiState .collectAsState().value

    when (ressult) {
        is Response.Loading -> {
            //  isLodingAndTryBtn(1)

        }
        is Response.Success -> {
            ressult.data?.let {
                SharedPref.getInstance(context).putString(TOKEN,it.access_token)
            }
        }
        is Response.Error -> {
            // Toast.makeText(this, it.errorMesssage.toString(), Toast.LENGTH_SHORT).show()
            //  callLogin()
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_xl")
@Composable
fun GreetingPreview() {
    ComposeApplicationTheme {
        SignInUi()
    }
}