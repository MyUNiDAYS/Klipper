package com.myunidays.klipper.flipper.perflogger

class NoOpFlipperPerfLogger : FlipperPerfLogger {
    override fun startMarker(name: String) { }
    override fun endMarker(name: String) { }
    override fun cancelMarker(name: String) { }
}
