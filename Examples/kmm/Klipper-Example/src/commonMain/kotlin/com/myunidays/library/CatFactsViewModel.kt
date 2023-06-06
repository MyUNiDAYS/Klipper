package com.myunidays.library

import com.myunidays.klipper.FlipperClient
import com.myunidays.klipper.FlipperPlugin
import com.myunidays.klipper.plugins.sharedpreferences.createUserDefaultsPlugin
import com.myunidays.klipper.plugins.network.NetworkFlipperPlugin
import com.russhwolf.settings.Settings
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.launch

expect fun saveData(context: Any? = null, data: String)

expect val client: HttpClient

expect val networkPlugin : NetworkFlipperPlugin

class CatFactsViewModel(context: Any? = null) {

    init {
        val flipperClient = FlipperClient.getInstance(context)
        flipperClient.start()
        flipperClient.addPlugin(networkPlugin as FlipperPlugin)
//        flipperClient.addPlugin(createUserDefaultsPlugin(context, "default") as FlipperPlugin)
        flipperClient.addPlugin(createUserDefaultsPlugin(context, "com.myunidays.klipperexample_preferences") as FlipperPlugin)
    }

    fun makeNetworkRequest(context: Any? = null) {
        kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Default).launch {
            val response = client.get("https://catfact.ninja/fact")
            saveData(context, response.body())
        }

    }
}
