package com.example.composebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebasics.ui.theme.ComposeBasicsTheme

class TestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            TestApp()
        }
    }
}

@Composable
fun TestApp(){
    ComposeBasicsTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                Greeting("Android")
                Divider()
                CountButton()
//                Divider()
//                CountStateButton()
            }
        }
    }
}

@Composable
fun CountButton(){
//    var count : Int = 0
    var countState by remember {
        mutableStateOf(0)
    }

    Button(onClick = { countState++ }) {
        Text(text = "버튼을 ${countState}번 눌렀어요")
    }
}

@Composable
fun CountStateButton(){
    var countState by remember {
        mutableStateOf(0)
    }

    Button(onClick = { countState++ },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)) {
        Text(text = "버튼을 ${countState}번 눌렀어요", color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun TestActivityPreview(){
    TestApp()
}