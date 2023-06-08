package com.myunidays.klipper.plugins.network

import io.ktor.client.plugins.api.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.content.*

fun NetworkFlipperPlugin.createKtorPlugin() = createClientPlugin("FlipperKtorPlugin") {
    on(SendingRequest) { request, content ->
        handleSendRequest(request, content)
    }

    onResponse { response ->
        handleOnResponse(response)
    }
}

internal expect fun NetworkFlipperPlugin.handleSendRequest(request: HttpRequestBuilder, content: OutgoingContent)
internal expect suspend fun NetworkFlipperPlugin.handleOnResponse(response: HttpResponse)
