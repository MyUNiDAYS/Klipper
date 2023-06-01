package com.myunidays.klipper

import cocoapods.FlipperKit.FlipperConnectionProtocol
import cocoapods.FlipperKit.FlipperPluginProtocol
import platform.darwin.NSObject

internal class FlipperPluginImpl internal constructor(private val ios: FlipperPlugin) : NSObject(), FlipperPluginProtocol {
    override fun didConnect(connection: FlipperConnectionProtocol?) =
        ios.didConnect(connection)

    override fun didDisconnect() = ios.didDisconnect()

    override fun identifier(): String? = ios.identifier()
}
