package com.myunidays.klipper.plugins.network

import com.facebook.flipper.plugins.network.NetworkReporter
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.content.*
import io.ktor.util.*
import java.util.*

internal actual fun NetworkFlipperPlugin.handleSendRequest(
    request: HttpRequestBuilder,
    content: OutgoingContent
) {
    val info = NetworkReporter.RequestInfo()
    val identifier = UUID.randomUUID().toString()
    info.requestId = identifier
    info.uri = request.url.toString()
    info.method = request.method.value
    info.timeStamp = System.currentTimeMillis()
//    info.timeStamp = request.re .timestamp
//    info.headers = request.headers .map {
//        NetworkReporter.Header(it.key, it.value.joinToString())
//    }
//    info.body = content.
    reportRequest(info)
}

//private RequestInfo convertRequest(
//      Request request, final Buffer bodyBuffer, final String identifier) throws IOException {
//    final List<NetworkReporter.Header> headers = convertHeader(request.headers());
//    final RequestInfo info = new RequestInfo();
//    info.requestId = identifier;
//    info.timeStamp = System.currentTimeMillis();
//    info.headers = headers;
//    info.method = request.method();
//    info.uri = request.url().toString();
//    if (bodyBuffer != null) {
//      info.body = bodyBufferToByteArray(bodyBuffer, mMaxBodyBytes);
//      bodyBuffer.close();
//    }
//
//    return info;
//  }

internal actual suspend fun NetworkFlipperPlugin.handleOnResponse(response: HttpResponse) {
    val info = NetworkReporter.ResponseInfo()
    val identifier = UUID.randomUUID().toString()
    info.requestId = identifier
    info.timeStamp = response.responseTime.timestamp
    info.statusCode = response.status.value
    info.headers = response.headers.toMap().map {
        NetworkReporter.Header(it.key, it.value.joinToString())
    }
//    info.body = response.body()
    reportResponse(info)
}

//@Throws(IOException::class)
//private fun convertResponse(
//    response: Response, bodyBuffer: Buffer?, identifier: String, isMock: Boolean
//): NetworkReporter.ResponseInfo {
//    if (bodyBuffer != null) {
//        info.body = FlipperOkhttpInterceptor.bodyBufferToByteArray(bodyBuffer, mMaxBodyBytes)
//        bodyBuffer.close()
//    }
//    return info
//}