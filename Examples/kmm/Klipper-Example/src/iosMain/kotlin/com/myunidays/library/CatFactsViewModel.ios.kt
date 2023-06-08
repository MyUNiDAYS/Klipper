package com.myunidays.library

import com.myunidays.klipper.plugins.network.createKtorPlugin
import platform.Foundation.NSUserDefaults
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import com.myunidays.klipper.plugins.network.createNetworkFlipperPlugin


actual fun saveData(context: Any?, data: String) {
    NSUserDefaults.standardUserDefaults.setObject(data, "fact")
}

actual val networkPlugin = createNetworkFlipperPlugin()

actual val client = HttpClient() {
    install(ContentNegotiation) { json() }
    expectSuccess = true
    developmentMode = true
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
    install(networkPlugin.createKtorPlugin())
}
