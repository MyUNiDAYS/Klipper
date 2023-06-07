package com.myunidays.klipper.plugins.network

import io.ktor.client.*
import io.ktor.client.plugins.api.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.content.*

fun HttpClientConfig<*>.installFlipperKtorPlugin(networkFlipperPlugin: NetworkFlipperPlugin) {
    install(networkFlipperPlugin.createKtorPlugin())
}

internal fun NetworkFlipperPlugin.createKtorPlugin() = createClientPlugin("FlipperKtorPlugin") {
    on(SendingRequest) { request, content ->
        // would need to push to the network plugin, how do we do this?
        handleSendRequest(request, content)
    }

    onResponse { response ->
        // push to network plugin
        handleOnResponse(response)
    }
}

internal expect fun NetworkFlipperPlugin.handleSendRequest(request: HttpRequestBuilder, content: OutgoingContent)
internal expect fun NetworkFlipperPlugin.handleOnResponse(response: HttpResponse)
