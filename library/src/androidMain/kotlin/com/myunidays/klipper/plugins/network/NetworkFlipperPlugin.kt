package com.myunidays.klipper.plugins.network

import com.facebook.flipper.core.FlipperConnection
import com.myunidays.klipper.FlipperPlugin

actual class NetworkFlipperPlugin : FlipperPlugin {

    internal val android = com.facebook.flipper.plugins.network.NetworkFlipperPlugin()
    override fun getId(): String = android.id

    override fun onConnect(connection: FlipperConnection?) {
        android.onConnect(connection)
    }

    override fun onDisconnect() {
        android.onDisconnect()
    }

    override fun runInBackground(): Boolean = android.runInBackground()
}

actual fun createNetworkFlipperPlugin() = NetworkFlipperPlugin()
