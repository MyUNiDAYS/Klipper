package com.myunidays.library

import com.myunidays.klipper.FlipperClient
import com.myunidays.klipper.FlipperPlugin
import com.myunidays.klipper.plugins.network.createNetworkFlipperPlugin
import com.myunidays.klipper.plugins.sharedpreferences.createUserDefaultsPlugin
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.launch

expect fun saveData(context: Any? = null, data: String)

class CatFactsViewModel {

    init {
        val flipperClient = FlipperClient.getInstance(null)
        flipperClient.start()
        flipperClient.addPlugin(createNetworkFlipperPlugin() as FlipperPlugin)
        flipperClient.addPlugin(createUserDefaultsPlugin(null) as FlipperPlugin)
    }

    fun makeNetworkRequest(context: Any? = null) {
        val client = HttpClient() {
            install(ContentNegotiation) { json() }
            expectSuccess = true
            developmentMode = true
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
        kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Default).launch {
            val response = client.get("https://catfact.ninja/fact")
            saveData(context, response.body())
        }

    }
}