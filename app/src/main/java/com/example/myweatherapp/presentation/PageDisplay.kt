package com.example.myweatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myweatherapp.icons.IconManager
import com.example.myweatherapp.icons.models.IconModel
import com.example.myweatherapp.icons.models.IconSize

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
                        CreateButton {
                            GetIcon(IconManager.shareIcon, IconSize.extraSmall)
                        }
                        CreateButton {
                            GetIcon(IconManager.settingsIcon, IconSize.extraSmall)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        content(Modifier.padding(paddingValues))
    }
}

@Composable
fun GetIcon(icon: IconModel? = null, size: Dp) {
    if (icon != null) {
        Image(
            painter = painterResource(id = icon.imageVector),
            contentDescription = icon.description,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(size)
                .aspectRatio(1f)
        )
    } else {
        Text("Icono no encontrado")
    }
}

@Composable
private fun CreateButton(content: @Composable (Modifier) -> Unit) {
    Button(
        onClick = {TODO()},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .padding(end = 8.dp)
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