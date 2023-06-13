package com.myunidays.klipper.plugins.network

import cocoapods.FlipperKit.FlipperConnectionProtocol
import cocoapods.FlipperKit.SKRequestInfo
import cocoapods.FlipperKit.SKResponseInfo
import com.myunidays.klipper.FlipperPlugin
import platform.darwin.NSObject

fun initWithNetworkAdapter(
    networkAdapter: SKNetworkAdapter?
): NetworkFlipperPlugin =
    NetworkFlipperPlugin(
        cocoapods.FlipperKit.FlipperKitNetworkPlugin(networkAdapter = networkAdapter?.ios)
    )

actual class NetworkFlipperPlugin internal constructor(
    val ios: cocoapods.FlipperKit.FlipperKitNetworkPlugin = cocoapods.FlipperKit.FlipperKitNetworkPlugin()
) : FlipperPlugin, NetworkReporter, NSObject() {
    override fun didConnect(connection: FlipperConnectionProtocol?) {
        ios.didConnect(connection)
    }

    override fun didDisconnect() {
        ios.didDisconnect()
    }

    override fun identifier(): String? = ios.identifier()

    override fun didObserveRequest(request: SKRequestInfo?) {
        ios.didObserveRequest(request)
    }

    override fun didObserveResponse(response: SKResponseInfo?) {
        ios.didObserveResponse(response)
    }

    companion object {
        fun init(networkAdapter: SKNetworkAdapter? = null) = initWithNetworkAdapter(networkAdapter)
    }
}

fun createNetworkFlipperPlugin(networkAdapter: SKNetworkAdapter? = null) =
    NetworkFlipperPlugin.init(networkAdapter)
actual fun createNetworkFlipperPlugin() = NetworkFlipperPlugin.init()

actual typealias NetworkReporter = cocoapods.FlipperKit.SKNetworkReporterDelegateProtocol
