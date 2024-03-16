package com.imsosoft.kotlinflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.imsosoft.kotlinflow.ui.theme.KotlinFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinFlowTheme {

                val viewModel by viewModels<MyViewModel>()

                FirstScreen(viewModel = viewModel)

            }
        }
    }
}

@Composable
fun FirstScreen(viewModel: MyViewModel) {

    val counter = viewModel.countDownTimer.collectAsState(initial = 10)

    Surface(color = MaterialTheme.colorScheme.background) {
        Box(modifier = Modifier.fillMaxSize()) {
            CustomText(text = counter.value.toString(), Modifier.align(Alignment.Center))
        }
    }

}

@Composable
fun CustomText(text: String, modifier: Modifier) {

    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontSize = 30.sp,
        modifier = modifier
    )

}
