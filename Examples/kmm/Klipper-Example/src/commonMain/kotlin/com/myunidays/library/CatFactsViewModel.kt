package com.myunidays.library

import com.myunidays.klipper.FlipperClient
import com.myunidays.klipper.FlipperPlugin
import com.myunidays.klipper.plugins.flows.*
import com.myunidays.klipper.plugins.sharedpreferences.createUserDefaultsPlugin
import com.myunidays.klipper.plugins.network.NetworkFlipperPlugin
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

expect fun saveData(context: Any? = null, data: String)

expect val client: HttpClient

expect val networkPlugin : NetworkFlipperPlugin

class CatFactsViewModel(private val context: Any? = null) {

    val scope: CoroutineScope = CoroutineScope(Dispatchers.Main + Job())

    val exampleStateFlow: MutableStateFlow<String> = MutableStateFlow("Initial Value")
    val exampleSharedFlow: MutableSharedFlow<String> = MutableSharedFlow()

    fun startClient() {
        val flipperClient = FlipperClient.getInstance(context)
        flipperClient.start()
        flipperClient.addPlugin(networkPlugin as FlipperPlugin)
        val flowPlugin = FlowPlugin()
        flipperClient.addPlugin(flowPlugin as FlipperPlugin)
        flipperClient.addPlugin(createUserDefaultsPlugin(context, "com.myunidays.klipperexample_preferences") as FlipperPlugin)
        flowPlugin.addFlow(exampleStateFlow)
        flowPlugin.addFlow(exampleSharedFlow)
        scope.launch {
            delay(5000)
            println("Setting value")
            exampleStateFlow.emit("New Value")
            delay(5000)
            println("Setting value")
            exampleStateFlow.emit("New Value2")
        }
        scope.launch {
            delay(5000)
            println("Setting value")
            exampleSharedFlow.emit("New Value")
            delay(5000)
            println("Setting value")
            exampleSharedFlow.emit("New Value2")
        }
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
        client.get("https://picsum.photos/200/300")
    }
}
