package com.myunidays.klipper

fun sharedClient(): FlipperClient = FlipperClient.sharedClient()

actual class FlipperClient internal constructor(val ios: cocoapods.FlipperKit.FlipperClient) {
    actual fun start() = ios.start()
    actual fun stop() = ios.stop()

    actual fun getPlugin(id: String): FlipperPlugin? =
        ios.pluginWithIdentifier(id) as? FlipperPlugin

    actual fun addPlugin(plugin: FlipperPlugin) {
        ios.addPlugin(FlipperPluginImpl(plugin))
    }

    actual companion object {
        actual fun getInstance(context: Any?): FlipperClient =
            FlipperClient(cocoapods.FlipperKit.FlipperClient.sharedClient()!!)
        fun sharedClient(): FlipperClient = getInstance(null)
    }
}
