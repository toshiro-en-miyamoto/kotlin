package com.example.quadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.quadrant.ui.theme.QuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuadrantTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ComposableInfo(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ComposableInfoCard(
    modifier: Modifier = Modifier,
    title: String = "??? Title ???",
    description: String = "??? description ???",
    backgroundColor: Color = Color.Red,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        )
        Text(
            text = description,
            fontSize = TextUnit.Unspecified,
            textAlign = TextAlign.Justify,
        )
    }
}

@Composable
fun ComposableInfo(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            ComposableInfoCard(
                title = stringResource(R.string.text_composable_title),
                description =  stringResource(R.string.text_composable_description),
                modifier = Modifier.weight(1f),
                backgroundColor = Color(0xFFEADDFF)
            )
            ComposableInfoCard(
                title = stringResource(R.string.image_composable_title),
                description =  stringResource(R.string.image_composable_description),
                modifier = Modifier.weight(1f),
                backgroundColor = Color(0xFFD0BCFF)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            ComposableInfoCard(
                title = stringResource(R.string.row_composable_title),
                description = stringResource(R.string.row_composable_description),
                modifier = Modifier.weight(1f),
                backgroundColor =  Color(0xFFB69DF8)
            )
            ComposableInfoCard(
                title = stringResource(R.string.column_composable_title),
                description = stringResource(R.string.column_composable_description),
                modifier = Modifier.weight(1f),
                backgroundColor = Color(0xFFF6EDFF)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuadrantTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            // Quadrant(modifier = Modifier.padding(innerPadding))
            ComposableInfo(modifier = Modifier.padding(innerPadding))
        }
    }
}
