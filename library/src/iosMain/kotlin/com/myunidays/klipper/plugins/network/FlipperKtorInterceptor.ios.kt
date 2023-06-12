package com.myunidays.klipper.plugins.network

import cocoapods.FlipperKit.SKRequestInfo
import cocoapods.FlipperKit.SKResponseInfo
import com.myunidays.klipper.toData
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.content.*
import io.ktor.util.*
import io.ktor.util.date.*
import platform.Foundation.*

internal actual suspend fun NetworkFlipperPlugin.handleSendRequest(request: HttpRequestBuilder, content: OutgoingContent) {
    val info = SKRequestInfo()
    val identifier = NSUUID()
    info.identifier = identifier.hash.toLong()
    info.timestamp = GMTDate().timestamp.toULong()
    val infoRequest = NSMutableURLRequest(NSURL(string = request.url.toString()))
    request.headers.append("requestId", identifier.UUIDString)
    request.headers.build().toMap().forEach {
        infoRequest.setValue(
            it.value.joinToString(","),
            it.key
        )
    }
    infoRequest.HTTPBody = when (content) {
        is OutgoingContent.ByteArrayContent ->
            content.bytes()
        is OutgoingContent.ReadChannelContent ->
            content.readFrom().toByteArray()
        is OutgoingContent.WriteChannelContent -> ByteArray(0)
        else -> ByteArray(0)
    }.toData()
    info.request = infoRequest
    didObserveRequest(info)
}
@OptIn(InternalAPI::class)
internal actual suspend fun NetworkFlipperPlugin.handleOnResponse(response: HttpResponse) {
    val identifier = NSUUID(uUIDString = response.call.request.headers["requestId"]!!)
    val infoResponse = NSHTTPURLResponse(
        uRL = NSURL(string = response.call.request.url.toString()),
        statusCode = response.status.value.toLong(),
        HTTPVersion = null,
        headerFields = response.headers.toMap().mapValues { it.value.joinToString(",") }
    )
    val info = SKResponseInfo()
    info.setIdentifier(identifier.hash.toLong())
    info.setTimestamp(response.responseTime.timestamp.toULong())
    info.setResponse(infoResponse)
    info.setBodyFromData(response.content.toByteArray().toData())
    didObserveResponse(info)
}
