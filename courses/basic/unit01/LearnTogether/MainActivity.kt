package com.example.learntogether

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learntogether.ui.theme.LearnTogetherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            LearnTogetherTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    LearningTopic(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LearningTopic(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
    ) {
        Image(
            painter = painterResource(R.drawable.bg_compose_background),
            contentDescription = null,
        )
        Text(
            text = stringResource(R.string.topic_title),
            fontSize = 24.sp,
            modifier = modifier.padding(16.dp),
        )
        Text(
            text = stringResource(R.string.topic_summary),
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(16.dp),
        )
        Text(
            text = stringResource(R.string.topic_text),
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(16.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LearnTogetherTheme {
            LearningTopic(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
