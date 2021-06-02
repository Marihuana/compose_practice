package com.example.composebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasics.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyApp(content : @Composable () -> Unit){
    ComposeBasicsTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun MyScreenContent(names : List<String> = List(1000) { "Hello Android $it" }){
    var counterState by remember{
        mutableStateOf(0)
    }

    Column(modifier = Modifier.fillMaxHeight()) {
        NamesList(names = names, modifier = Modifier.weight(1f))

        Counter(
            count = counterState,
            updateCount = { newCount ->
                counterState = newCount
            }
        )
        if(counterState > 5){
            Text(text = "난 숫자 세는걸 좋아해")
        }
        
//        Greeting(name = "Android")
//        Divider()
//        Greeting(name = "There")
    }
}

@Composable
fun NamesList(names: List<String>, modifier: Modifier){
    //이게 렌더링 안하는 부분은 메모리에 안올림
    LazyColumn(modifier = modifier) {
       items(items = names){
           Greeting(name = it)
           Divider()
       }

    }
//    Column(modifier = modifier) {
//        for(name : String in names){
//            Greeting(name = name)
//            Divider()
//        }
//    }
}



@Composable
fun Counter(count : Int, updateCount : (Int) -> Unit){

    Button(onClick = { updateCount(count + 1) }, modifier = Modifier.padding(12.dp)) {
        Text(text = "나는 ${count}번 클릭했다")
    }
}

@Composable
fun Greeting(name: String) {
    var isSelected : Boolean by remember {
        mutableStateOf(false)
    }

    val targetColor by animateColorAsState(
        targetValue = if(isSelected) MaterialTheme.colors.primary  else Color.Transparent,
        animationSpec = tween(durationMillis = 2000)
    )
//    val targetColor = if(isSelected) Color.Red else Color.Transparent

    Surface(color = targetColor) {
        Text(text = "Hello $name!",
            modifier = Modifier
//            .background(color = Color.Magenta)
                .clickable { isSelected = !isSelected }
                .padding(15.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}