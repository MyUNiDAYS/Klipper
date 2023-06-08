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
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.myunidays.library.CatFactsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

class NativeViewModel(private val context: Context) : ViewModel() {

    lateinit var networkFlipperPlugin: NetworkFlipperPlugin

    init {
        AndroidFlipperClient.getInstance(context).stop()
    }

    fun startClient() {
        val client = AndroidFlipperClient.getInstance(context)
        networkFlipperPlugin = NetworkFlipperPlugin()
        client.addPlugin(networkFlipperPlugin)
        client.addPlugin(SharedPreferencesFlipperPlugin(context, "defaults"))
        client.start()
    }
    fun makeNetworkRequest() {
        CoroutineScope(Dispatchers.Default).launch {
            val networkClient = OkHttpClient.Builder()
                .addNetworkInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin))
                .build()
            val request = Request.Builder()
                .url("https://catfact.ninja/fact")
                .build()
            val response = networkClient.newCall(request)
                .execute()
                .use { response -> response.body?.string() ?: "" }
            val sharedPref = context.getSharedPreferences("defaults", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("fact", response)
                apply()
            }
        }
    }
}

@Composable
fun NativeView(viewModel: NativeViewModel = NativeViewModel(context = LocalContext.current)) {

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
            Text(text = "Native Make network request")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}