package com.myunidays.klipper.plugins.network

fun create(): SKNetworkAdapter = SKNetworkAdapter()
class SKNetworkAdapter {
    internal val ios = cocoapods.FlipperKit.SKIOSNetworkAdapter()
}