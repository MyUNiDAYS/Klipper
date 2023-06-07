package com.myunidays.klipper.plugins.network

import com.facebook.flipper.core.FlipperConnection
import com.myunidays.klipper.FlipperPlugin

actual class NetworkFlipperPlugin : FlipperPlugin, NetworkReporter {

    internal val android = com.facebook.flipper.plugins.network.NetworkFlipperPlugin()
    override fun getId(): String = android.id

    override fun onConnect(connection: FlipperConnection?) {
        android.onConnect(connection)
    }

    override fun onDisconnect() {
        android.onDisconnect()
    }

    override fun runInBackground(): Boolean = android.runInBackground()
    override fun reportRequest(requestInfo: com.facebook.flipper.plugins.network.NetworkReporter.RequestInfo?) {
        android.reportRequest(requestInfo)
    }

    override fun reportResponse(responseInfo: com.facebook.flipper.plugins.network.NetworkReporter.ResponseInfo?) {
        android.reportResponse(responseInfo)
    }
}

actual fun createNetworkFlipperPlugin() = NetworkFlipperPlugin()

actual typealias NetworkReporter = com.facebook.flipper.plugins.network.NetworkReporter