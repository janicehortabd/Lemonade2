package com.example.lemonade2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.lemonade2.ui.theme.Lemonade2Theme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lemonade2Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun Greeting( modifier: Modifier = Modifier) {
    var step by remember { mutableStateOf(1) }
    var qtdToDo by remember { mutableStateOf(0) }
    var qtdDid by remember { mutableStateOf(0) }
    val imageResource = when(step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }
    val imageDescription = when(step) {
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.glass_of_lemonade
        4 -> R.string.empty_glass
        else -> R.string.lemon_tree
    }
    val TextDescription = when(step) {
        1 -> R.string.tap_the_lemon_tree_to_select_a_lemon
        2 -> R.string.keep_tapping_the_lemon_to_squeeze_it
        3 -> R.string.tap_the_lemonade_to_drink_it
        4 -> R.string.tap_the_empty_glass_to_start_again
        else -> R.string.tap_the_lemon_tree_to_select_a_lemon
    }


    Column(modifier = modifier,verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
            ){
          Button(onClick = {
              if(qtdToDo !=0){

                  if(qtdToDo != qtdDid) qtdDid++
                  else{
                      qtdDid = 0
                      qtdToDo = 0
                      step++
                  }
              }else {
                  if (step < 4) {
                      if (step == 2) qtdToDo = (2..4).random()
                      else step++
                  } else step = 1
              }

          }) {
              Column (modifier= modifier){


                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = stringResource(id = imageDescription))
              }
                Text(stringResource(id = TextDescription), fontSize = 18.sp)



         }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    Lemonade2Theme {
        Greeting(modifier = Modifier.fillMaxSize() )
    }
}