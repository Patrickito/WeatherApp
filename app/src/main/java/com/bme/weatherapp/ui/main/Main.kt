package com.bme.weatherapp.ui.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bme.weatherapp.R
import com.bme.weatherapp.model.City
import com.bme.weatherapp.ui.about.About
import com.bme.weatherapp.ui.details.Details
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.math.roundToInt


@Composable
fun MainScreen() {

    val navController = rememberNavController()

    val colors = MaterialTheme.colors
    val systemUiController = rememberSystemUiController()

    var statusBarColor by remember { mutableStateOf(colors.primaryVariant) }
    var navigationBarColor by remember { mutableStateOf(colors.primaryVariant) }

    val animatedStatusBarColor by animateColorAsState(
        targetValue = statusBarColor,
        animationSpec = tween()
    )
    val animatedNavigationBarColor by animateColorAsState(
        targetValue = navigationBarColor,
        animationSpec = tween()
    )

    NavHost(navController = navController, startDestination = NavScreen.Home.route) {
        composable(NavScreen.Home.route) {
            Cities(
                viewModel = hiltViewModel(),
                selectCity = {
                    navController.navigate("${NavScreen.CityDetails.route}/$it")
                },
                about = {
                    navController.navigate("${NavScreen.About.route}/$it")
                },
            )

            LaunchedEffect(Unit) {
                statusBarColor = colors.primaryVariant
                navigationBarColor = colors.primaryVariant
            }
        }
        composable(
            route = NavScreen.CityDetails.routeWithArgument,
            arguments = listOf(
                navArgument(NavScreen.CityDetails.argument0) { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val cityId =
                backStackEntry.arguments?.getLong(NavScreen.CityDetails.argument0)
                    ?: return@composable

            Details(cityId = cityId, viewModel = hiltViewModel()) {
                navController.navigateUp()
            }

            LaunchedEffect(Unit) {
                statusBarColor = Color.Transparent
                navigationBarColor = colors.background
            }
        }
        composable(
            route = NavScreen.About.routeWithArgument,
            arguments = listOf(
                navArgument(NavScreen.About.argument0) { type = NavType.LongType }
            )
        ) { backStackEntry ->

            About(viewModel = hiltViewModel()) {
                navController.navigateUp()
            }

            LaunchedEffect(Unit) {
                statusBarColor = Color.Transparent
                navigationBarColor = colors.background
            }
        }
    }

    LaunchedEffect(animatedStatusBarColor, animatedNavigationBarColor) {
        systemUiController.setStatusBarColor(animatedStatusBarColor)
        systemUiController.setNavigationBarColor(animatedNavigationBarColor)
    }
}

sealed class NavScreen(val route: String) {

    object Home : NavScreen("Home")
    object About : NavScreen("About") {
        const val routeWithArgument: String = "About/{aboutId}"

        const val argument0: String = "aboutId"
    }

    object CityDetails : NavScreen("CityDetails") {

        const val routeWithArgument: String = "CityDetails/{cityId}"

        const val argument0: String = "cityId"
    }
}

@Composable
fun Cities(
    viewModel: MainViewModel,
    selectCity: (Long) -> Unit,
    about: (Long) -> Unit
) {
    val cities: List<City> by viewModel.cityList.collectAsState(initial = listOf())

    val searchText = remember { mutableStateOf("") }

    ConstraintLayout {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.BottomCenter)
                .offset(y = with(LocalDensity.current) { (-32).dp })
                .padding(20.dp)
        ) {
            Button(onClick = { about(0) }) {
                Text("About")
            }
        }

        Column(
            Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(700.dp)
        ) {

            Row {
                TextField(
                    value = searchText.value,
                    onValueChange = { searchText.value = it },
                    modifier = Modifier
                        .width(240.dp)
                        .padding(top = 15.dp, start = 15.dp, end = 0.dp, bottom = 15.dp),
                    singleLine = true,
                    label = { Text(text = "Search") },
                )

                Column {
                    Surface(
                        modifier = Modifier
                            .size(90.dp)
                            .padding(top = 11.dp, start = 0.dp, end = 15.dp, bottom = 15.dp)
                            .clickable(
                                onClick = { selectCity(1) }
                            )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_gps_fixed_24),
                            contentDescription = "gps",
                            modifier = Modifier
                                .size(80.dp)
                                .padding(0.dp),
                        )
                    }
                }


            }

            LazyColumn {
                items(cities) { city ->

                    println(city.main)

                    val cityName = city.cityName
                    println("City name: $cityName")
                    if (cityName.contains(searchText.value, ignoreCase = true)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                                .clickable(onClick = { selectCity(city.id) }),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = city.cityName,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .width(140.dp)
                                    .padding(0.dp)
                            )

                            Text(
                                text = ((city.temp * 10.0).roundToInt() / 10.0).toString() + " °C",
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .width(100.dp)
                                    .padding(10.dp)
                            )

                            Image(
                                painter =

                                if (city.main == "Clouds" || city.main == "Rain" || city.main == "Snow")
                                    painterResource(id = R.drawable.baseline_cloud_24)
                                else if (city.main == "Clear")
                                    painterResource(id = R.drawable.baseline_wb_sunny_24)
                                else
                                    painterResource(id = R.drawable.baseline_wb_sunny_24),

                                contentDescription = "icon"
                            )
                        }
                    }
                }
            }

        }
    }
}
