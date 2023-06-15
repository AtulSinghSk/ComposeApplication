package com.example.composeapplication.tools

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.composeapplication.R
import com.example.composeapplication.tools.ui.theme.ComposeApplicationTheme
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class DialogBoxActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    DeleteDialogBox()
                   // BottomSeet()
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DeleteDialogBox(

) {
    /*negativeButtonColor: Color = Color(0xFF35898F),
    positiveButtonColor: Color = Color(0xFFFF0000),
    spaceBetweenElements: Dp = 18.dp,*/
   var context = LocalContext.current.applicationContext
    var dialogOpen by remember { mutableStateOf(false) }

    if (dialogOpen) {
        Dialog(onDismissRequest = {
            dialogOpen = false
        }
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(0.92f),
                color = Color.Transparent // dialog background
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    // text and buttons
                    Column(
                        modifier = Modifier
                            .padding(top = 30.dp) // this is the empty space at the top
                            .fillMaxWidth()
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(percent = 10)
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(height = 36.dp))

                        Text(
                            text = "Delete?",
                            fontSize = 24.sp
                        )

                        Spacer(modifier = Modifier.height(height = 40.dp))

                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Are you sure, you want to delete the item?",
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(height = 40.dp))

                        // buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            DialogButton(buttonColor = Color.Red, buttonText = "No") {
                                Toast
                                    .makeText(context, "No Click", Toast.LENGTH_SHORT)
                                    .show()
                                dialogOpen = false
                            }
                            DialogButton(buttonColor = Color.Green, buttonText = "Yes") {
                                Toast
                                    .makeText(context, "Yes Click", Toast.LENGTH_SHORT)
                                    .show()
                                dialogOpen = false
                            }
                        }

                        // If you decrease the Surface's width, increase this height
                        Spacer(modifier = Modifier.height(height = 40.dp * 2))
                    }

                    // delete icon

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_edit_note_24),
                        contentDescription = "Delete Icon",
                        tint = Color.Blue,
                        modifier = Modifier
                            .background(color = Color.White, shape = CircleShape)
                            .border(width = 2.dp, shape = CircleShape, color = Color.Blue)
                            .padding(all = 16.dp)
                            .align(alignment = Alignment.TopCenter)
                    )
                }
            }
        }
    }

    Button(onClick = { dialogOpen = true }, modifier = Modifier.wrapContentHeight()) {
        Text(text = "OPEN DIALOG")
    }

    /*Button(onClick = {}, modifier = Modifier.wrapContentHeight()) {
        Text(text = "Bottom Dilog")
    }*/
}

@Composable
fun DialogButton(
    cornerRadiusPercent: Int = 26,
    buttonColor: Color,
    buttonText: String,
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = buttonColor,
                shape = RoundedCornerShape(percent = cornerRadiusPercent)
            )
            .clickable {
                onDismiss()
            }
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Text(
            text = buttonText,
            color = Color.White,
            fontSize = 18.sp,
        )
    }
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterialApi
@Composable
fun BottomSeet() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("GFG | Bottom Sheet", color = Color.Green) }, modifier =Modifier.background(Color(0xff0f9d58),shape = RoundedCornerShape(10.dp)))},
        content ={ MyContent()},

    )
}

@ExperimentalMaterialApi
@Composable
fun MyContent(){
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState =
    BottomSheetState(BottomSheetValue.Collapsed))

    // Declaring Coroutine scope
    val coroutineScope = rememberCoroutineScope()

    // Creating a Bottom Sheet
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent =  {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color(0XFF0F9D58),shape = RoundedCornerShape(10.dp,10.dp,0.dp,0.dp))) {
                Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Hello Geek!", fontSize = 20.sp, color = Color.White)
                }
            }
        },

        sheetPeekHeight = 0.dp,
    ){}

    Column(
        Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        // bottomSheetScaffoldState value upon click
        // when bottomSheetScaffoldState is collpased,
        // it expands and vice-versa
        Button(onClick = {
            coroutineScope.launch {
                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed){
                    bottomSheetScaffoldState.bottomSheetState.expand()
                }else{
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            }
        }, colors = ButtonDefaults.buttonColors(Color(0XFF0F9D58))) {
            Text(text = "Click Me", color = Color.White)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    ComposeApplicationTheme {
        DeleteDialogBox()
       // BottomSeet()
    }
}