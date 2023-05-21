package com.myunidays.klipper.flipper.perflogger

interface FlipperPerfLogger {
    fun startMarker(name: String)
    fun endMarker(name: String)
    fun cancelMarker(name: String)
}
