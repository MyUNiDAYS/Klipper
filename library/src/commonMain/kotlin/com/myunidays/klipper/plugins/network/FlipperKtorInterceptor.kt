package com.myunidays.klipper.plugins.network

import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.api.SendingRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.http.content.OutgoingContent
import io.ktor.util.toByteArray

fun NetworkFlipperPlugin.createKtorPlugin() = createClientPlugin("FlipperKtorPlugin") {
    on(SendingRequest) { request, content ->
        handleSendRequest(request, content)
    }

    onResponse { response ->
        handleOnResponse(response)
    }
}

internal expect suspend fun NetworkFlipperPlugin.handleSendRequest(
    request: HttpRequestBuilder,
    content: OutgoingContent
)
internal expect suspend fun NetworkFlipperPlugin.handleOnResponse(response: HttpResponse)

const val NETWORK_REQUEST_KEY = "ktor-request-id"

suspend fun OutgoingContent.byteArray(): ByteArray = when (this) {
    is OutgoingContent.ByteArrayContent -> bytes()
    is OutgoingContent.ReadChannelContent -> readFrom().toByteArray()
    is OutgoingContent.WriteChannelContent -> ByteArray(0)
    else -> ByteArray(0)
}
