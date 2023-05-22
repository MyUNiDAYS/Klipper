package com.myunidays.klipper

import android.content.Context

actual class FlipperClient internal constructor(val android: com.facebook.flipper.core.FlipperClient) {
    actual companion object {
        actual fun getInstance(context: Any?): FlipperClient =
            FlipperClient(com.facebook.flipper.android.AndroidFlipperClient.getInstance(context as Context))
    }
}
