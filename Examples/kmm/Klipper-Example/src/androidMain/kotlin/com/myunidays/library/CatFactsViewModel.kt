package com.myunidays.library

import android.content.SharedPreferences
import android.content.Context

actual fun saveData(context: Any?, data: String) {
    (context as Context).let { context ->
        val sharedPref = context.getSharedPreferences("defaults", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("fact", data)
            apply()
        }
    }
}
