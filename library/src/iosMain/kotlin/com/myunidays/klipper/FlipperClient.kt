package com.myunidays.klipper


actual class FlipperClient internal constructor(val ios: cocoapods.FlipperKit.FlipperClient) {
    actual companion object {
        actual fun getInstance(context: Any?): FlipperClient =
            FlipperClient(cocoapods.FlipperKit.FlipperClient.sharedClient()!!)
    }

}
