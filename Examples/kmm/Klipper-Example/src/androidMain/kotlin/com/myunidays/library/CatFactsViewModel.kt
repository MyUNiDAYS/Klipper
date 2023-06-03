package com.myunidays.library

import com.myunidays.klipper.plugins.sharedpreferences.createSharedPreferencesPlugin

actual fun saveData(context: Any?, data: String) {
    createSharedPreferencesPlugin(context)
}
