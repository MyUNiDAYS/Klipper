package com.myunidays.klipperexample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun MainView(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {
            navController.navigate("native")
        }) {
            Text(text = "Native")
        }
        Button(onClick = {
            navController.navigate("kmm")
        }) {
            Text(text = "KMM")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}