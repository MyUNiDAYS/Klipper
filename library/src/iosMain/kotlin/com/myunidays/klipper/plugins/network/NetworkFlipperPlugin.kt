package com.myunidays.klipper.plugins.network

import cocoapods.FlipperKit.FlipperConnectionProtocol
import com.myunidays.klipper.FlipperPlugin
import platform.darwin.NSObject

fun initWithNetworkAdapter(
    networkAdapter: SKNetworkAdapter
): NetworkFlipperPlugin =
    NetworkFlipperPlugin(
        cocoapods.FlipperKit.FlipperKitNetworkPlugin(networkAdapter = networkAdapter.ios)
    )

actual class NetworkFlipperPlugin internal constructor(val ios: cocoapods.FlipperKit.FlipperKitNetworkPlugin) : FlipperPlugin, NSObject() {
    override fun didConnect(connection: FlipperConnectionProtocol?) {
        ios.didConnect(connection)
    }

    override fun didDisconnect() {
        ios.didDisconnect()
    }

    override fun identifier(): String? = ios.identifier()
}
