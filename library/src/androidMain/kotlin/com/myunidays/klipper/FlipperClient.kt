package com.myunidays.klipper

import android.content.Context

actual class FlipperClient internal constructor(val android: com.facebook.flipper.core.FlipperClient) {
    actual fun start() = android.start()
    actual fun stop() = android.stop()
    actual fun getPlugin(id: String): FlipperPlugin? =
        android.getPlugin(id)
    actual fun addPlugin(plugin: FlipperPlugin) {
        android.addPlugin(plugin)
    }
    actual fun removePlugin(plugin: FlipperPlugin) {
        android.removePlugin(plugin)
    }

    actual companion object {
        actual fun getInstance(context: Any?): FlipperClient =
            FlipperClient(com.facebook.flipper.android.AndroidFlipperClient.getInstance(context as Context))
    }
}
