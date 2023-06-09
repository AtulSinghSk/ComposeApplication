package com.example.composeapplication.tools

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeapplication.tools.FirebaseActivity.Companion.context
import com.example.composeapplication.tools.FirebaseActivity.Companion.myRef
import com.example.composeapplication.tools.ui.theme.ComposeApplicationTheme
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import org.w3c.dom.Comment

class FirebaseActivity : ComponentActivity() {
    companion object {
        val database = com.google.firebase.ktx.Firebase.database
        val myRef = database.getReference("message")
        lateinit var context:Context
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
                    context= LocalContext.current
                    Firebase()

                }
            }
        }
    }

}


@Composable
fun Firebase() {
    Column() {
        Button(onClick = { InsertData("1","Atulsi","atul@gmail.com") }) {
            Text(text = "InsertData")
        }
        Button(onClick = { ShowData() }) {
            Text(text = "ShowData")

        }
        Button(onClick = { api() }) {
            Text(text = "Api")

        }
    }

}

fun InsertData(userId: String, name: String, email: String) {
    // Write a message to the database
    //  myRef.setValue("Hello, World!")
    val user = User(name, email)
    myRef.child("users").push().child(userId).setValue(user)
  //  myRef.child("users").child(userId).child("username").setValue(name)

}

fun ShowData() {
    // Read from the database
    myRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            val value = dataSnapshot.getValue<String>()
            Log.d("TAG", "Value is: $value")
        }

        override fun onCancelled(error: DatabaseError) {
            // Failed to read value
            Log.w("TAG", "Failed to read value.", error.toException())
        }
    })
}
fun api() {
    val childEventListener = object : ChildEventListener {
        override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
            Log.d("TAG", "onChildAdded:" + dataSnapshot.key!!)

            // A new comment has been added, add it to the displayed list
            val comment = dataSnapshot.getValue<Comment>()

            // ...
        }

        override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
            Log.d("TAG", "onChildChanged: ${dataSnapshot.key}")

            // A comment has changed, use the key to determine if we are displaying this
            // comment and if so displayed the changed comment.
            val newComment = dataSnapshot.getValue<Comment>()
            val commentKey = dataSnapshot.key

            // ...
        }

        override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            Log.d("TAG", "onChildRemoved:" + dataSnapshot.key!!)

            // A comment has changed, use the key to determine if we are displaying this
            // comment and if so remove it.
            val commentKey = dataSnapshot.key

            // ...
        }

        override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
            Log.d("TAG", "onChildMoved:" + dataSnapshot.key!!)

            // A comment has changed position, use the key to determine if we are
            // displaying this comment and if so move it.
            val movedComment = dataSnapshot.getValue<Comment>()
            val commentKey = dataSnapshot.key

            // ...
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.w("TAG", "postComments:onCancelled", databaseError.toException())
            Toast.makeText(
                context,
                "Failed to load comments.",
                Toast.LENGTH_SHORT,
            ).show()
        }
    }
    myRef.addChildEventListener(childEventListener)
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    ComposeApplicationTheme {

    }
}