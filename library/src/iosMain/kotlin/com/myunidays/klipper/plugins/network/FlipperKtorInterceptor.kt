package com.myunidays.klipper.plugins.network

import cocoapods.FlipperKit.SKRequestInfo
import cocoapods.FlipperKit.SKResponseInfo
import io.ktor.client.plugins.api.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.content.*

internal actual fun NetworkFlipperPlugin.handleSendRequest(request: HttpRequestBuilder, content: OutgoingContent) {
    this.didObserveRequest(SKRequestInfo())
}
internal actual fun NetworkFlipperPlugin.handleOnResponse(response: HttpResponse) {
    this.didObserveResponse(SKResponseInfo())
}
