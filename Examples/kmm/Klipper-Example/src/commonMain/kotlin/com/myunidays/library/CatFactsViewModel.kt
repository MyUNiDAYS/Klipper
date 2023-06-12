package com.myunidays.library

import com.myunidays.klipper.FlipperClient
import com.myunidays.klipper.FlipperPlugin
import com.myunidays.klipper.plugins.sharedpreferences.createUserDefaultsPlugin
import com.myunidays.klipper.plugins.network.NetworkFlipperPlugin
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

expect fun saveData(context: Any? = null, data: String)

expect val client: HttpClient

expect val networkPlugin : NetworkFlipperPlugin

class CatFactsViewModel(private val context: Any? = null) {

    fun startClient() {
        val flipperClient = FlipperClient.getInstance(context)
        flipperClient.start()
        flipperClient.addPlugin(networkPlugin as FlipperPlugin)
        flipperClient.addPlugin(createUserDefaultsPlugin(context, "com.myunidays.klipperexample_preferences") as FlipperPlugin)
    }

    fun closeClient() {
        FlipperClient.getInstance(context).let {
            it.removePlugin(networkPlugin as FlipperPlugin)
            it.stop()
        }
    }

    suspend fun makeNetworkRequest() {
        val response = client.get("https://catfact.ninja/fact")
        saveData(context, response.body())
    }
}
