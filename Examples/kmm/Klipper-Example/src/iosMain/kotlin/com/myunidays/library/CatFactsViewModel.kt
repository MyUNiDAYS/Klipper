package com.myunidays.library

import platform.Foundation.NSUserDefaults
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import com.myunidays.klipper.plugins.network.createNetworkFlipperPlugin


actual fun saveData(context: Any?, data: String) {
    NSUserDefaults.standardUserDefaults.setObject(data, "fact")
}

actual val client = HttpClient() {
    install(ContentNegotiation) { json() }
    expectSuccess = true
    developmentMode = true
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
}
actual val networkPlugin = createNetworkFlipperPlugin()

//import com.myunidays.klipper.plugins.network.NetworkFlipperPlugin
//
//actual fun initialiseKlipper() {
////    val flipperClient = com.myunidays.klipper.FlipperClient.getInstance(null)
////    SKNetworkA
////    NetworkFlipperPlugin.init()
////    flipperClient.addPlugin()
//}
