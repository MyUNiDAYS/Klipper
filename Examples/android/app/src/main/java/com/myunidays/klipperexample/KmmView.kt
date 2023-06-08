package com.myunidays.klipperexample

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.myunidays.library.CatFactsViewModel

class KMMViewModel(context: Context) : ViewModel() {
    val viewModel = CatFactsViewModel(context = context)

    init {
        viewModel.closeClient()
    }
    fun startClient() = viewModel.startClient()
    fun makeNetworkRequest() = viewModel.makeNetworkRequest()
}

@Composable
fun KmmView(viewModel: KMMViewModel = KMMViewModel(context = LocalContext.current)) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {
            viewModel.startClient()
        }) {
            Text(text = "Connect to flipper")
        }
        Button(onClick = {
            viewModel.makeNetworkRequest()
        }) {
            Text(text = "KMM Make network request")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
