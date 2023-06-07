package com.myunidays.klipper.plugins.network

import cocoapods.FlipperKit.FlipperConnectionProtocol
import cocoapods.FlipperKit.SKRequestInfo
import cocoapods.FlipperKit.SKResponseInfo
import com.myunidays.klipper.FlipperPlugin
import platform.darwin.NSObject

fun initWithNetworkAdapter(
    networkAdapter: SKNetworkAdapter
): NetworkFlipperPlugin =
    NetworkFlipperPlugin(
        cocoapods.FlipperKit.FlipperKitNetworkPlugin(networkAdapter = networkAdapter.ios)
    )

actual class NetworkFlipperPlugin internal constructor(val ios: cocoapods.FlipperKit.FlipperKitNetworkPlugin) : FlipperPlugin, NetworkReporter, NSObject() {
    override fun didConnect(connection: FlipperConnectionProtocol?) {
        ios.didConnect(connection)
    }

    override fun didDisconnect() {
        ios.didDisconnect()
    }

    override fun identifier(): String? = ios.identifier()

    companion object {
        fun init(networkAdapter: SKNetworkAdapter) = initWithNetworkAdapter(networkAdapter)
    }

    override fun didObserveRequest(request: SKRequestInfo?) {
        ios.didObserveRequest(request)
    }

    override fun didObserveResponse(response: SKResponseInfo?) {
        ios.didObserveResponse(response)
    }
}

actual fun createNetworkFlipperPlugin() = NetworkFlipperPlugin.init(SKNetworkAdapter())

actual typealias NetworkReporter = cocoapods.FlipperKit.SKNetworkReporterDelegateProtocol