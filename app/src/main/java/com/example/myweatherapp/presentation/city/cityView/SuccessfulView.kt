package com.example.myweatherapp.presentation.city.cityView

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myweatherapp.presentation.PageView
import com.example.myweatherapp.repository.models.City

@Composable
fun SuccessfulView(
    cities: List<City>,
    onCitySelected: (City) -> Unit,
    onSearch: (String) -> Unit
) {
    val input = remember { mutableStateOf("") }

    PageView { modifier ->
        Column (
            modifier = modifier.padding(15.dp)
        ) {
            TextField(
                value = input.value,
                label = { Text("Texto") },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    input.value = it
                    onSearch(input.value)
                }
            )
            LoadCityList(cities) { selectedCity ->
                onCitySelected(selectedCity)
            }
        }
    }
}

@Composable
private fun LoadCityList(cities: List<City>, onSelect: (City) -> Unit) {
    LazyColumn {
        items(items = cities) {city ->
            CityItem(city, onSelect)
        }
    }
}

@Composable
private fun CityItem(city: City, onSelect: (City) -> Unit) {
    Card(onClick = { onSelect(city) }) {
        Text("${city.name}...")
    }
}
