package com.myunidays.library

import android.content.SharedPreferences
import android.content.Context
import com.myunidays.klipper.plugins.network.FlipperOkhttpInterceptor
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
//    (context as Context).let {
//        val sharedPref = context.getSharedPreferences("defaults", Context.MODE_PRIVATE)
//        with(sharedPref.edit()) {
//            putString("fact", data)
//            apply()
//        }
//    }
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
    engine {
        addInterceptor(okhttpInterceptor)
    }
}

