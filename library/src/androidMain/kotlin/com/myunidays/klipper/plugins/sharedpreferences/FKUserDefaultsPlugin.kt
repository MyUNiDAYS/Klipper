package com.myunidays.klipper.plugins.sharedpreferences

import android.content.Context

actual fun createUserDefaultsPlugin(context: Any?, name: String): FKUserDefaultsPlugin = FKUserDefaultsPlugin(context as Context, name)

actual typealias FKUserDefaultsPlugin = SharedPreferencesFlipperPlugin
