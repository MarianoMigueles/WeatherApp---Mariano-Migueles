package com.example.myweatherapp.presentation.city

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.myweatherapp.presentation.PageView
import com.example.myweatherapp.presentation.events.LoadingView
import com.example.myweatherapp.presentation.events.EmptyView
import com.example.myweatherapp.presentation.events.ErrorView
import com.example.myweatherapp.repository.models.City

@Composable
fun CityView(
    state: CityState,
    execute: (CityIntention) -> Unit
) {
    var input by remember { mutableStateOf("") }

    TextField(
        value = input,
        label = { Text("Texto") },
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {
            input = it
            execute(CityIntention.Search(it))
        }
    )
    when(state) {
        is CityState.Loading -> LoadingView()
        is CityState.Error ->  ErrorView(state.message)
        is CityState.Success -> LoadCityList(state.cities) {
            execute(CityIntention.Select(it))
        }
        is CityState.Empty -> EmptyView()
    }

}

@Composable
private fun LoadCityList(cities: List<City>, onSelect: (City) -> Unit) {
    val modifier = Modifier
    Column(
        modifier = modifier.fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (city in cities) {
            Card(
                modifier = modifier.fillMaxWidth().height(50.dp),
                colors = CardColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.secondary,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Black
                ),
                shape = RectangleShape,
                onClick = { onSelect(city) }
            ) {
                Text(city.name)
                HorizontalDivider(
                    modifier = modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Spacer(modifier.height(1.dp))
        }
    }
}
