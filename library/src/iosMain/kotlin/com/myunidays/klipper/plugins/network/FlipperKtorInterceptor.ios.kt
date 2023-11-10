package com.myunidays.klipper.plugins.network

import cocoapods.FlipperKit.SKRequestInfo
import cocoapods.FlipperKit.SKResponseInfo
import com.myunidays.klipper.toData
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.http.content.OutgoingContent
import io.ktor.util.InternalAPI
import io.ktor.util.date.GMTDate
import io.ktor.util.toByteArray
import io.ktor.util.toMap
import platform.Foundation.HTTPBody
import platform.Foundation.NSHTTPURLResponse
import platform.Foundation.NSMutableURLRequest
import platform.Foundation.NSURL
import platform.Foundation.NSUUID
import platform.Foundation.setValue

internal actual suspend fun NetworkFlipperPlugin.handleSendRequest(
    request: HttpRequestBuilder,
    content: OutgoingContent
) {
    SKRequestInfo().apply {
        val identifier = NSUUID()
        setIdentifier(identifier.hash.toLong())
        setTimestamp(GMTDate().timestamp.toULong())
        NSMutableURLRequest(NSURL(string = request.url.toString())).apply {
            request.headers.append(NETWORK_REQUEST_KEY, identifier.UUIDString)
            request.headers.build().toMap().forEach {
                setValue(
                    it.value.joinToString(","),
                    it.key
                )
            }
            HTTPBody = content.byteArray().toData()
        }.also {
            setRequest(it)
        }
    }.also {
        didObserveRequest(it)
    }
}
@OptIn(InternalAPI::class)
internal actual suspend fun NetworkFlipperPlugin.handleOnResponse(response: HttpResponse) {
    SKResponseInfo().apply {
        val identifier = NSUUID(uUIDString = response.call.request.headers[NETWORK_REQUEST_KEY]!!)
        val infoResponse = NSHTTPURLResponse(
            uRL = NSURL(string = response.call.request.url.toString()),
            statusCode = response.status.value.toLong(),
            HTTPVersion = null,
            headerFields = response.headers.toMap().mapValues { it.value.joinToString(",") }
        )
        setIdentifier(identifier.hash.toLong())
        setTimestamp(response.responseTime.timestamp.toULong())
        setResponse(infoResponse)
        setBodyFromData(response.content.toByteArray().toData())
    }.also {
        didObserveResponse(it)
    }
}
