package com.example.myweatherapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myweatherapp.presentation.city.CityPage
import com.example.myweatherapp.presentation.weather.WeatherPage
import com.example.myweatherapp.router.NavTarget

@Composable
fun MainPage() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = NavTarget.Cities.id
    ) {

        composable(
            route = NavTarget.Cities.id
        ) {
            CityPage(navHostController)
        }

        composable(
            route = "clima?lat={lat}&lon={lon}&name={name}",
            arguments = listOf(
                navArgument("lat") { type = NavType.FloatType },
                navArgument("lon") { type = NavType.FloatType },
                navArgument("name") { type = NavType.StringType }
            )
        ) {
            val lat = it.arguments?.getFloat("lat") ?: 0.0f
            val lon = it.arguments?.getFloat("lon") ?: 0.0f
            val name = it.arguments?.getString("name") ?: ""
            WeatherPage(navHostController, lat, lon, name)
        }

    }
}