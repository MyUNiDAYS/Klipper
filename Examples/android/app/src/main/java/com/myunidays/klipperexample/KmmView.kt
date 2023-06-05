package com.myunidays.klipperexample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.myunidays.library.CatFactsViewModel

@Composable
fun KmmView() {

    val context = LocalContext.current
    val viewModel = CatFactsViewModel(context = context)

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {
            viewModel.makeNetworkRequest(context = context)
        }) {
            Text(text = "KMM Make network request")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
