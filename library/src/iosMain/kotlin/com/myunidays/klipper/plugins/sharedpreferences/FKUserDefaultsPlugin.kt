package com.myunidays.klipper.plugins.sharedpreferences

import cocoapods.FlipperKit.FlipperConnectionProtocol
import com.myunidays.klipper.FlipperPlugin
import platform.darwin.NSObject

actual fun createUserDefaultsPlugin(context: Any?, name: String) : FKUserDefaultsPlugin = initWithName(name)

fun initWithName(
    name: String? = null
): FKUserDefaultsPlugin =
    FKUserDefaultsPlugin(name)

actual class FKUserDefaultsPlugin(name: String?) : FlipperPlugin, NSObject() {
    private val ios = cocoapods.FlipperKit.FKUserDefaultsPlugin(name)
    override fun didConnect(connection: FlipperConnectionProtocol?) {
        ios.didConnect(connection)
    }

    override fun didDisconnect() {
        ios.didDisconnect()
    }

    override fun identifier(): String? = ios.identifier()
}
