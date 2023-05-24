package com.bme.weatherapp.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bme.weatherapp.R
import com.bme.weatherapp.model.City
import com.bme.weatherapp.ui.main.MainViewModel
import kotlin.math.roundToInt

@Composable
fun Details(
    cityId: Long,
    viewModel: DetailsViewModel,
    pressOnBack: () -> Unit = {}
) {
    LaunchedEffect(key1 = cityId) {
        viewModel.loadCityById(cityId)
    }

    val details: City? by viewModel.cityDetailsFlow.collectAsState(initial = null)
    viewModel.addFavoriteFlow.collectAsState(initial = Unit)

    details?.let { city ->
        CityDetailsBody(viewModel, city, pressOnBack)
    }
}

@Composable
private fun CityDetailsBody(
    viewModel: DetailsViewModel,
    city: City,
    pressOnBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {

        ConstraintLayout {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.BottomCenter)
                    .offset(y = with(LocalDensity.current) { (-32).dp })
                    .padding(20.dp)
            ) {
                Button(onClick = { viewModel.addFavorite(city.id) }) {
                    Text("Add favorite")
                }
            }

            Column {
                Text(
                    text = city.cityName,
                    style = MaterialTheme.typography.h1,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontSize = 50.sp,
                    modifier = Modifier
                        .padding(start = 40.dp, top = 100.dp)
                )

                Row {
                    Text(
                        text = ((city.temp * 10.0).roundToInt() / 10.0).toString() + " °C",
                        fontSize = 40.sp,
                        modifier = Modifier
                            .width(200.dp)
                            .padding(start = 40.dp, top = 50.dp, end = 0.dp, bottom = 50.dp)
                    )

                    Image(
                        painter =

                        if (city.main == "Clouds" || city.main == "Rain" || city.main == "Snow")
                            painterResource(id = R.drawable.baseline_cloud_24)
                        else if (city.main == "Clear")
                            painterResource(id = R.drawable.baseline_wb_sunny_24)
                        else
                            painterResource(id = R.drawable.baseline_wb_sunny_24),

                        contentDescription = "icon",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(start = 0.dp, top = 50.dp, end = 0.dp, bottom = 0.dp)
                    )
                }

                Text(
                    text = "Pressure: " + city.pressure.toString() + " hPa",
                    style = MaterialTheme.typography.h1,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(start = 40.dp, top = 12.dp)
                )

                Text(
                    text = "Wind: " + city.wind.toString() + " m/s",
                    style = MaterialTheme.typography.h1,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(start = 40.dp, top = 12.dp)
                )


            }
        }
    }
}

//
////            NetworkImage(
////                url = poster.poster,
////                modifier = Modifier
////                    .constrainAs(image) {
////                        top.linkTo(parent.top)
////                    }
////                    .fillMaxWidth()
////                    .aspectRatio(0.85f),
////                circularRevealEnabled = true,
////                paletteLoadedListener = { palette = it }
////            )
////
////            ColorPalettes(
////                palette = palette,
////                modifier = Modifier
////                    .constrainAs(paletteRow) {
////                        top.linkTo(image.bottom)
////                    }
////            )
////

////
////            Text(
////                text = poster.description,
////                style = MaterialTheme.typography.body2,
////                modifier = Modifier
////                    .constrainAs(content) {
////                        top.linkTo(title.bottom)
////                    }
////                    .padding(16.dp)
////            )
////
////            Text(
////                text = "Gif",
////                style = MaterialTheme.typography.h2,
////                textAlign = TextAlign.Center,
////                modifier = Modifier
////                    .padding(8.dp)
////                    .constrainAs(gifTitle) {
////                        top.linkTo(content.bottom)
////                    }
////            )
////
////            CoilImage(
////                imageModel = { poster.gif },
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .padding(8.dp)
////                    .height(500.dp)
////                    .clip(RoundedCornerShape(8.dp))
////                    .constrainAs(gif) {
////                        top.linkTo(gifTitle.bottom)
////                    },
////            )
////
////            ImageBalloonAnchor(
////                reference = image,
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .aspectRatio(0.85f),
////                content = poster.name,
////                onClick = { balloon, anchor -> balloon.showAlignBottom(anchor) }
////            )
////
////            Icon(
////                imageVector = Icons.Filled.ArrowBack,
////                tint = Color.White,
////                contentDescription = null,
////                modifier = Modifier
////                    .constrainAs(arrow) {
////                        top.linkTo(parent.top)
////                    }
////                    .padding(12.dp)
////                    .statusBarsPadding()
////                    .clickable(onClick = { pressOnBack() })
////            )
//        }


//@Composable
//private fun ColorPalettes(
//    palette: Palette?,
//    modifier: Modifier
//) {
//    val colorList: List<Int> = palette.paletteColorList()
//    LazyRow(
//        modifier = modifier
//            .padding(horizontal = 8.dp, vertical = 16.dp)
//    ) {
//        items(colorList) { color ->
//            Crossfade(
//                targetState = color,
//                modifier = Modifier
//                    .padding(horizontal = 8.dp)
//                    .size(45.dp)
//            ) {
//                Box(
//                    modifier = Modifier
//                        .background(color = Color(it))
//                        .fillMaxSize()
//                )
//            }
//        }
//    }
//}