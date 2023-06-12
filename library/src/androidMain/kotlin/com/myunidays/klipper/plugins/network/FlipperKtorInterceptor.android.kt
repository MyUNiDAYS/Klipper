package com.myunidays.klipper.plugins.network

import com.facebook.flipper.plugins.network.NetworkReporter
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.content.*
import io.ktor.util.*
import io.ktor.util.date.*
import java.util.*

internal actual suspend fun NetworkFlipperPlugin.handleSendRequest(
    request: HttpRequestBuilder,
    content: OutgoingContent
) {
    val info = NetworkReporter.RequestInfo()
    val identifier = UUID.randomUUID().toString()
    info.requestId = identifier
    info.uri = request.url.toString()
    info.method = request.method.value
    info.timeStamp = GMTDate().timestamp
    request.headers.append("requestId", identifier)
    info.headers = request.headers.build().toMap().map {
        NetworkReporter.Header(it.key, it.value.joinToString())
    }
    info.body = when (request.body) {
        is OutgoingContent.ByteArrayContent ->
            (request.body as OutgoingContent.ByteArrayContent).bytes()
        is OutgoingContent.ReadChannelContent ->
            (request.body as OutgoingContent.ReadChannelContent).readFrom().toByteArray()
        is OutgoingContent.WriteChannelContent -> ByteArray(0)
        else -> ByteArray(0)
    }
    reportRequest(info)
}

@OptIn(InternalAPI::class)
internal actual suspend fun NetworkFlipperPlugin.handleOnResponse(response: HttpResponse) {
    val info = NetworkReporter.ResponseInfo()
    val identifier = response.call.request.headers["requestId"]!!
    info.requestId = identifier
    info.timeStamp = response.responseTime.timestamp
    info.statusCode = response.status.value
    info.headers = response.headers.toMap().map {
        NetworkReporter.Header(it.key, it.value.joinToString())
    }
    response.content.awaitContent()
    info.body = response.content.toByteArray()
    reportResponse(info)
}
