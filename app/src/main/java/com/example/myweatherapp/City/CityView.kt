package com.example.myweatherapp.Ciudad

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myweatherapp.R
import com.example.myweatherapp.ui.theme.fontInknutAntiqua

@Composable
fun CityView(
    modifier: Modifier = Modifier,
    state: CityState,
    execute: (CityIntention) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        when(state) {
            is CityState.Loading -> LoadingView()
            is CityState.Error ->  ErrorView(state.message)
            is CityState.Success -> SuccessfulView()
            is CityState.Empty -> EmptyView()
        }
    }
}

@Composable
private fun EventView(content: @Composable () -> Unit) {
    val modifier: Modifier = Modifier

    Column(
        modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}

@Composable
private fun EventText(text: String) {
    Text(
        text,
        fontFamily = fontInknutAntiqua,
        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
        fontWeight = MaterialTheme.typography.headlineLarge.fontWeight
    )
}

@Composable
private fun EventImage(iconState: String, description: String) {

    val iconResId = when (iconState) {
        "empty" -> R.drawable.wind
        "error" -> R.drawable.error
        "load" -> R.drawable.loading
        else -> null
    }

    if (iconResId != null) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = description,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .aspectRatio(1f)
                .alpha(0.9f),
            contentScale = ContentScale.Crop
        )
    } else {
        Text("Icono no encontrado")
    }

}

@Composable
fun EmptyView() {
    EventView {
        EventImage("empty", "Icono de contenido vacio.")
        EventText("¡Se lo llevo el viento!")
        Spacer(modifier = Modifier.height(1.dp))
        EventText("No hay nada aqui")
    }
}

@Composable
fun SuccessfulView() {
    Text("Exito al cargar")
}

@Composable
fun ErrorView(message: String) {
    EventView {
        EventImage("error", "Icono de error.")
        EventText("¡Ha ocurrido un error!")
        Spacer(modifier = Modifier.height(1.dp))
        Text(message)
    }
}

@Composable
fun LoadingView() {
    EventView {
        EventImage("load", "Icono de contenido cargando.")
        EventText("Cargando...")
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyViewPreview() {
    MaterialTheme {
        EmptyView()
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingViewPreview() {
    MaterialTheme {
        LoadingView()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorViewPreview() {
    MaterialTheme {
        ErrorView("Error")
    }
}