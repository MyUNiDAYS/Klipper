package com.myunidays.library

import com.myunidays.klipper.plugins.network.FlipperOkhttpInterceptor
import com.myunidays.klipper.plugins.network.createKtorPlugin
import com.myunidays.klipper.plugins.network.createNetworkFlipperPlugin
import com.russhwolf.settings.Settings
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*

val settings: Settings = Settings()

actual fun saveData(context: Any?, data: String) {
    println("Attempting to save data $data")
    settings.putString("fact", data)
}

actual val networkPlugin = createNetworkFlipperPlugin()
val okhttpInterceptor = FlipperOkhttpInterceptor(networkPlugin)

actual val client = HttpClient(OkHttp) {
    install(ContentNegotiation) { json() }
    expectSuccess = true
    developmentMode = true
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
    install(networkPlugin.createKtorPlugin())
}

