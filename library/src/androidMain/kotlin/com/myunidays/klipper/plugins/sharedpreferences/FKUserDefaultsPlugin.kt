package com.myunidays.klipper.plugins.sharedpreferences

import android.content.Context

actual fun createUserDefaultsPlugin(context: Any?) : FKUserDefaultsPlugin = FKUserDefaultsPlugin(context as Context, "")

actual typealias FKUserDefaultsPlugin = SharedPreferencesFlipperPlugin
