package com.myunidays.klipper.plugins.sharedpreferences

actual fun createSharedPreferencesPlugin(context: Any?) : SharedPreferencesFlipperPlugin = SharedPreferencesFlipperPlugin(null)

actual typealias SharedPreferencesFlipperPlugin = FKUserDefaultsPlugin
