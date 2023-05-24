package com.bme.weatherapp.ui.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun About(
    viewModel: AboutViewModel,
    pressOnBack: () -> Unit = {}
) {
    AboutBody(pressOnBack)
}

@Composable
private fun AboutBody(
    pressOnBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxHeight()
            .wrapContentSize(Alignment.Center)
            .padding(30.dp)
    ) {

        ConstraintLayout {
            Text(
                text = "App: Weather App\n" +
                        "Desc: This is a weather application\n" +
                        "Developer: Patrik Gál\n" +
                        "Neptun: C9XRX3",
                style = MaterialTheme.typography.h1,
                fontSize = 30.sp,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}