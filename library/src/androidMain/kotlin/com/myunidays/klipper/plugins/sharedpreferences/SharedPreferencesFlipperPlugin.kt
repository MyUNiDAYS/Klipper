package com.myunidays.klipper.plugins.sharedpreferences

import android.content.Context
import com.facebook.flipper.core.FlipperConnection
import com.myunidays.klipper.FlipperPlugin

actual class SharedPreferencesFlipperPlugin(
    context: Context,
    name: String
) : FlipperPlugin {
    private val android = com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin(context, name)
    override fun getId(): String = android.id

    override fun onConnect(connection: FlipperConnection?) {
        android.onConnect(connection)
    }

    override fun onDisconnect() {
        android.onDisconnect()
    }

    override fun runInBackground(): Boolean =
        android.runInBackground()
}
