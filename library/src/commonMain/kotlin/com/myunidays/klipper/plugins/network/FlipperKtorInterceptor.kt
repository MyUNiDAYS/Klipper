package com.myunidays.klipper.plugins.network

import io.ktor.client.plugins.api.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.content.*
import io.ktor.util.*

fun NetworkFlipperPlugin.createKtorPlugin() = createClientPlugin("FlipperKtorPlugin") {
    on(SendingRequest) { request, content ->
        handleSendRequest(request, content)
    }

    onResponse { response ->
        handleOnResponse(response)
    }
}

internal expect suspend fun NetworkFlipperPlugin.handleSendRequest(request: HttpRequestBuilder, content: OutgoingContent)
internal expect suspend fun NetworkFlipperPlugin.handleOnResponse(response: HttpResponse)

const val NETWORK_REQUEST_KEY = "ktor-request-id"

suspend fun OutgoingContent.byteArray(): ByteArray = when (this) {
    is OutgoingContent.ByteArrayContent -> bytes()
    is OutgoingContent.ReadChannelContent -> readFrom().toByteArray()
    is OutgoingContent.WriteChannelContent -> ByteArray(0)
    else -> ByteArray(0)
}
