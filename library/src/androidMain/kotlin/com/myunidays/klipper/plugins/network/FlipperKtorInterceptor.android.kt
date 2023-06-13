package com.myunidays.klipper.plugins.network

import com.facebook.flipper.plugins.network.NetworkReporter
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers
import io.ktor.http.content.OutgoingContent
import io.ktor.util.InternalAPI
import io.ktor.util.date.GMTDate
import io.ktor.util.toByteArray
import io.ktor.util.toMap
import java.util.UUID

internal actual suspend fun NetworkFlipperPlugin.handleSendRequest(
    request: HttpRequestBuilder,
    content: OutgoingContent
) {
    NetworkReporter.RequestInfo().apply {
        val identifier = UUID.randomUUID().toString()
        requestId = identifier
        uri = request.url.toString()
        method = request.method.value
        timeStamp = GMTDate().timestamp
        request.headers.append(NETWORK_REQUEST_KEY, identifier)
        headers = request.headers.build().headerList()
        body = content.byteArray()
    }.also {
        reportRequest(it)
    }
}

@OptIn(InternalAPI::class)
internal actual suspend fun NetworkFlipperPlugin.handleOnResponse(response: HttpResponse) {
    NetworkReporter.ResponseInfo().apply {
        val identifier = response.call.request.headers[NETWORK_REQUEST_KEY]!!
        requestId = identifier
        timeStamp = response.responseTime.timestamp
        statusCode = response.status.value
        headers = response.headers.headerList()
        response.content.awaitContent()
        body = response.content.toByteArray()
    }.also {
        reportResponse(it)
    }
}

private fun Headers.headerList(): List<NetworkReporter.Header> = toMap()
    .map { NetworkReporter.Header(it.key, it.value.joinToString()) }
