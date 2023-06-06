package com.myunidays.klipper.plugins.network

import io.ktor.client.*
import io.ktor.client.plugins.api.*

val FlipperKtorPlugin = createClientPlugin("FlipperKtorPlugin") {
    on(SendingRequest) { request, content ->
        // would need to push to the network plugin
    }

    onResponse { response ->
        // push to network plugin
    }
}
