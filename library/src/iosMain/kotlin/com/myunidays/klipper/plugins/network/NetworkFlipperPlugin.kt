package com.myunidays.klipper.plugins.network

import cocoapods.FlipperKit.FlipperConnectionProtocol
import com.myunidays.klipper.FlipperPlugin
import platform.darwin.NSObject

actual class NetworkFlipperPlugin internal constructor(val ios: cocoapods.FlipperKit.FlipperKitNetworkPlugin) : FlipperPlugin, NSObject() {
    override fun didConnect(connection: FlipperConnectionProtocol?) {
        ios.didConnect(connection)
    }

    override fun didDisconnect() {
        ios.didDisconnect()
    }

    override fun identifier(): String? = ios.identifier()

    companion object {
        fun initWithNetworkAdapter(
            networkAdapter: cocoapods.FlipperKit.SKNetworkAdapterDelegateProtocol?,
            queue: platform.darwin.dispatch_queue_t
        ): NetworkFlipperPlugin =
            NetworkFlipperPlugin(
                cocoapods.FlipperKit.FlipperKitNetworkPlugin(
                    networkAdapter = networkAdapter,
                    queue = queue
                )
            )
    }
}
