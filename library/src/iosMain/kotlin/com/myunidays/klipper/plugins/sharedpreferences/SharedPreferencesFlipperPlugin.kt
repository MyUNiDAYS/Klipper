package com.myunidays.klipper.plugins.sharedpreferences

actual fun createSharedPreferencesPlugin(context: Any?, name: String): SharedPreferencesFlipperPlugin =
    SharedPreferencesFlipperPlugin(name)

actual typealias SharedPreferencesFlipperPlugin = FKUserDefaultsPlugin
