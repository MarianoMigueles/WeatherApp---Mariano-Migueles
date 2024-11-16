package com.example.myweatherapp.presentation

import android.content.Context
import android.content.Intent
import com.example.myweatherapp.repository.models.WeatherDTO

class ShareManager(private val context: Context) {

    fun shareWeatherStats(weather: WeatherDTO) {
        val message = """
            🌤️ Weather Update 🌤️
            Location: ${weather.name}
            Temperature: ${weather.main.temp}
            Description: ${weather.weather[0].description}
        """.trimIndent()

        shareMessege(message)
    }

    private fun shareMessege(messege: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, messege)
            type = "text/plain"
            putExtra(Intent.EXTRA_TITLE, "Weather Stats")
        }
        val shareIntent = Intent.createChooser(intent, null)
        context.startActivity(shareIntent)
    }

}