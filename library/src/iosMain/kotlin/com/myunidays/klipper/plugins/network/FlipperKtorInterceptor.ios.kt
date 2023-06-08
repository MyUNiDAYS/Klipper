package com.myunidays.klipper.plugins.network

import cocoapods.FlipperKit.SKRequestInfo
import cocoapods.FlipperKit.SKResponseInfo
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import platform.Foundation.*

internal actual fun NetworkFlipperPlugin.handleSendRequest(request: HttpRequestBuilder, content: OutgoingContent) {
    val info = SKRequestInfo()
    info.identifier = NSUUID().hash.toLong()
    info.timestamp = NSDate().timeIntervalSince1970.toULong()
    println("handleSendRequest time: ${info.timestamp}")
    info.request = NSURLRequest(NSURL(string = request.url.toString()))

//    val info = SKRequestInfo(
//        url = request.url.toString(),
//        method = request.method.value,
//        headers = request.headers.entries().map { it.key to it.value.joinToString(",") }.toMap(),
//        body = content.toString()
//    )
    // convert to NSURL Request

    println("handleSendRequest: $info")
//    didObserveRequest(info)
}
internal actual suspend fun NetworkFlipperPlugin.handleOnResponse(response: HttpResponse) {
    val info = SKResponseInfo()
    info.timestamp = NSDate().timeIntervalSince1970.toULong()
    println("handleOnResponse times: ${info.timestamp}")
    info.body = response.bodyAsText()
    println("handleOnResponse: ${info.body}")
    info.response = NSURLResponse(
        uRL = NSURL(string = response.call.request.url.toString()),
        MIMEType = response.contentType().toString(),
        expectedContentLength = response.contentLength() ?: 0,
        textEncodingName = response.charset().toString()
    )
//    didObserveResponse(info)
}
