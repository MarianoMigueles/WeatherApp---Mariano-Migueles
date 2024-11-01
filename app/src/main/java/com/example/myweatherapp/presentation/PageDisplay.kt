package com.example.myweatherapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageView(content: @Composable (Modifier) -> Unit) {
    val modifier: Modifier = Modifier

    Scaffold (
        modifier
            .fillMaxSize(),
        {
            TopAppBar(
                title = {
                    Row(
                        modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        CreateButton { Text("1") }
                        CreateButton { Text("2") }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {
        content(Modifier.padding(it))
    }
}

@Composable
private fun CreateButton(content: @Composable (Modifier) -> Unit) {
    Button(
        onClick = {TODO()},
        modifier = Modifier
            .padding(end = 8.dp)
            .background(Color.Transparent)
    )
    { content(Modifier) }
}

@Preview(showBackground = true)
@Composable
fun PageViewPreview() {
    MaterialTheme {
        PageView {
            Text("Todo mal")
        }
    }
}