package com.myunidays.klipper.plugins.network

import com.facebook.flipper.plugins.network.NetworkReporter
import io.ktor.client.plugins.api.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.content.*

internal fun NetworkFlipperPlugin.handleSendRequest(request: HttpRequestBuilder, content: OutgoingContent) {
    this.reportRequest(NetworkReporter.RequestInfo())
}
internal actual fun NetworkFlipperPlugin.handleOnResponse(response: HttpResponse) {
    this.reportResponse(NetworkReporter.ResponseInfo())
}
