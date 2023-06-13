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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KMMViewModel(context: Context) : ViewModel() {
    private val viewModel = CatFactsViewModel(context = context)

    init {
        runCatching {
            viewModel.closeClient()
        }
    }

    fun startClient() = viewModel.startClient()
    fun makeNetworkRequest() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.makeNetworkRequest()
        }
    }
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
