package com.myunidays.library

import platform.Foundation.NSUserDefaults

actual fun saveData(data: String) {
    NSUserDefaults.standardUserDefaults.setObject(data, "fact")
}

//import com.myunidays.klipper.plugins.network.NetworkFlipperPlugin
//
//actual fun initialiseKlipper() {
////    val flipperClient = com.myunidays.klipper.FlipperClient.getInstance(null)
////    SKNetworkA
////    NetworkFlipperPlugin.init()
////    flipperClient.addPlugin()
//}
