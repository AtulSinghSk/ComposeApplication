package com.example.composeapplication.tools

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.data.UiToolingDataApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.composeapplication.tools.FirebaseActivity.Companion.context
import com.example.composeapplication.tools.FirebaseActivity.Companion.myRef
import com.example.composeapplication.tools.ui.theme.ComposeApplicationTheme
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue

class FirebaseActivity : ComponentActivity() {
    companion object {
        val database = com.google.firebase.ktx.Firebase.database
        val myRef = database.getReference("Data")
        lateinit var context: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    context = LocalContext.current
                    //  Firebase()
                    DeleteDialogBox1()

                    //   FirebaseMessaging.getInstance().subscribeToTopic("notification")

                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertData() {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var mobileNumber by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current
    Column(modifier = Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = name,
            label = { Text(text = "name") },
            onValueChange = {
                name = it
                //  password1=it.toString()

            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            modifier = Modifier
                .padding(0.dp, 10.dp, 0.dp, 0.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White),
            shape = RoundedCornerShape(percent = 10),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Search
            )
        )

        OutlinedTextField(
            value = mobileNumber,
            label = { Text(text = "mobile Number") },
            onValueChange = {
                mobileNumber = it
                //  password1=it.toString()

            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            modifier = Modifier
                .padding(0.dp, 10.dp, 0.dp, 0.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White),
            shape = RoundedCornerShape(percent = 10),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Search
            )
        )

        OutlinedTextField(
            value = password,
            label = { Text(text = "Password") },
            onValueChange = {
                password = it
                //  password1=it.toString()

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

        Button(
            onClick = {
                //your onclick code here
                Log.e("TAG", "${mobileNumber.text.toString()}, ${password.text}: ")
                val user = User(name.text, mobileNumber.text, password.text)
                myRef.child("users detail").push().setValue(user)
                //  myRef.child("users").child(userId).child("username").setValue(name)
                if (validation(context, name, mobileNumber, password)) {
                    Log.e("TAG", "lotgIn Success: ")
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            shape = CircleShape
        ) {
            Text(text = "InsertData", fontSize = 18.sp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun updateData(index1: String) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var mobileNumber by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current
    Column(modifier = Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = name,
            label = { Text(text = "name") },
            onValueChange = {
                name = it
                //  password1=it.toString()

            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            modifier = Modifier
                .padding(0.dp, 10.dp, 0.dp, 0.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White),
            shape = RoundedCornerShape(percent = 10),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Search
            )
        )

        OutlinedTextField(
            value = mobileNumber,
            label = { Text(text = "mobile Number") },
            onValueChange = {
                mobileNumber = it
                //  password1=it.toString()

            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            modifier = Modifier
                .padding(0.dp, 10.dp, 0.dp, 0.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White),
            shape = RoundedCornerShape(percent = 10),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Search
            )
        )

        OutlinedTextField(
            value = password,
            label = { Text(text = "Password") },
            onValueChange = {
                password = it
                //  password1=it.toString()

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

        Button(
            onClick = {
                //your onclick code here
                Log.e("TAG", "${mobileNumber.text.toString()}, ${password.text}: ")
                val user = User(name.text, mobileNumber.text, password.text)
                myRef.child("users detail").child(index1.toString()).setValue(user)
                //  myRef.child("users").child(userId).child("username").setValue(name)
                if (validation(context, name, mobileNumber, password)) {
                    Log.e("TAG", "lotgIn Success: ")
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            shape = CircleShape
        ) {
            Text(text = "UpdateData", fontSize = 18.sp)
        }
    }
}

fun validation(
    context: Context,
    name: TextFieldValue,
    mobileNumber: TextFieldValue,
    password: TextFieldValue
): Boolean {
    if (name.text.isNullOrEmpty()) {
        Toast.makeText(context, "Mobile Number is required..!!", Toast.LENGTH_SHORT).show()
        return false
    }
    if (mobileNumber.text.isNullOrEmpty()) {
        Toast.makeText(context, "Mobile Number is required..!!", Toast.LENGTH_SHORT).show()
        return false
    }
    if (password.text.isNullOrEmpty()) {
        Toast.makeText(context, "Password is required..!!", Toast.LENGTH_SHORT).show()
        return false
    }
    return true
}

@SuppressLint("UnrememberedMutableState")
@OptIn(UiToolingDataApi::class)
@Composable
fun api() {
   // var userList1 = mutableStateListOf<User?>()
    var userList2 = mutableStateListOf<DataSnapshot>()
    val childEventListener = object : ChildEventListener {
        override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
            Log.d("TAG", "onChildAdded1:" + dataSnapshot)
            val comment = dataSnapshot.getValue<User>()
           // val comment1 = dataSnapshot.key<String>()
           // userList1.addAll(listOf(comment))
            userList2.addAll(listOf(dataSnapshot))

        }

        override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
            Log.d("TAG", "onChildChanged2: ${dataSnapshot.key}")
            val newComment = dataSnapshot.getValue<User>()
            val commentKey = dataSnapshot.key
            userList2.addAll(listOf(dataSnapshot))
        }

        override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            Log.d("TAG", "onChildRemoved3:" + dataSnapshot.key!!)
            val commentKey = dataSnapshot.key

        }

        override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
            Log.d("TAG", "onChildMoved4:" + dataSnapshot.key!!)
            val movedComment = dataSnapshot.getValue<User>()
            val commentKey = dataSnapshot.key

        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.w("TAG", "postComments:onCancelled5", databaseError.toException())
            Toast.makeText(
                context,
                "Failed to load comments.",
                Toast.LENGTH_SHORT,
            ).show()
        }
    }
    myRef.child("users detail").addChildEventListener(childEventListener)

    firebaseUI(userList2)
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DeleteDialogBox1(

) {
    var context = LocalContext.current.applicationContext
    var insertDataDialogOpen by remember { mutableStateOf(false) }
    var showDataDialogOpen by remember { mutableStateOf(false) }

    Column() {

        Button(onClick = { insertDataDialogOpen = true }, modifier = Modifier.wrapContentHeight()) {
            Text(text = "InsertData")
            if (insertDataDialogOpen) {
                Dialog(onDismissRequest = {
                    insertDataDialogOpen = false
                }
                ) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(0.92f),
                        //color = Color.Transparent // dialog background
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {

                            // text and buttons
                            InsertData()

                        }
                    }
                }
            }
        }

        Button(onClick = { showDataDialogOpen = true }, modifier = Modifier.wrapContentHeight()) {
            Text(text = "ShowtData")
            if (showDataDialogOpen) {
                Dialog(onDismissRequest = {
                    showDataDialogOpen = false
                }
                ) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(0.92f),
                        //color = Color.Transparent // dialog background
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {

                            // text and buttons
                            ShowData()
                        }
                    }
                }
            }
        }
        Button(onClick = { showDataDialogOpen = true }, modifier = Modifier.wrapContentHeight()) {
            Text(text = "ShowtListData")
            if (showDataDialogOpen) {
                Dialog(onDismissRequest = {
                    showDataDialogOpen = false
                }
                ) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(0.92f),
                        //color = Color.Transparent // dialog background
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {

                            // text and buttons
                            api()
                        }
                    }
                }
            }
        }
        Button(onClick = { showDataDialogOpen = true }, modifier = Modifier.wrapContentHeight()) {
            Text(text = "UpdateData")
            if (showDataDialogOpen) {
                Dialog(onDismissRequest = {
                    showDataDialogOpen = false
                }
                ) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(0.92f),
                        //color = Color.Transparent // dialog background
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {

                            // text and buttons
                            api()
                        }
                    }
                }
            }
        }
        InsertData()
        ShowData()
    }


}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ShowData() {
    var userList2 = mutableStateListOf<DataSnapshot>()
    var userList = mutableStateListOf<User?>()
    myRef.child("users detail").addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val children = dataSnapshot.children
            userList.clear()
            children.forEach {
                var snap = (it as DataSnapshot)
                var myTasksDTO = snap.getValue(User::class.java)
                with(myTasksDTO) {
                    this?.name = myTasksDTO!!.name
                    this?.mobile = myTasksDTO!!.mobile
                    this?.password = myTasksDTO!!.password

                }
                userList.add(myTasksDTO!!)
                userList2.addAll(listOf(snap))
            }
        }

        override fun onCancelled(error: DatabaseError) {
            Log.w("TAG", "Failed to read value.", error.toException())
        }

    })

   // firebaseUI(userList2)

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun firebaseUI(userList: SnapshotStateList<DataSnapshot>) {
    var UpdateDataDialogOpen by remember { mutableStateOf(false) }
    var indexState by remember {mutableStateOf("")}
    Log.e("TAG", "userList1: ${userList}")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(300.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            itemsIndexed(userList) { index, item ->
                Card(
                    onClick = {
                        Toast.makeText(
                            context,
                            userList[index]?.key + " selected..",
                            Toast.LENGTH_SHORT
                        ).show()
                        indexState = userList[index]?.key.toString()

                        UpdateDataDialogOpen = true

                    },
                    modifier = Modifier.padding(8.dp),
                    elevation = 6.dp

                ) {
                    if (UpdateDataDialogOpen) {
                        Dialog(onDismissRequest = {
                            UpdateDataDialogOpen = false
                        }
                        ) {
                            Surface(
                                modifier = Modifier.fillMaxWidth(0.92f),
                                //color = Color.Transparent // dialog background
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {

                                    // text and buttons
                                    updateData(indexState)
                                }
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.width(5.dp))
                        userList[index]?.getValue<User>()!!.name!!.let {
                            Text(
                                text = it,
                                modifier = Modifier.padding(4.dp),
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 20.sp, fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        userList[index]?.getValue<User>()!!.name!!.let {
                            Text(
                                text = it,
                                modifier = Modifier.padding(4.dp),
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 15.sp
                                )
                            )
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        userList[index].getValue<User>()!!.name!!.let {
                            Text(
                                text = it,
                                modifier = Modifier.padding(4.dp),
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                style = TextStyle(fontSize = 15.sp)
                            )
                        }
                    }
                }
            }

        }

    }
   // Log.e("TAG", "firebaseUI: $indexState")
  //  updateData(indexState)

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    ComposeApplicationTheme {

    }
}