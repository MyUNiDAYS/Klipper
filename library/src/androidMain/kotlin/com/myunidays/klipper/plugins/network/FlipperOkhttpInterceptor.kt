package com.myunidays.klipper.plugins.network

import okhttp3.Interceptor
import okhttp3.Response

class FlipperOkhttpInterceptor(networkFlipperPlugin: NetworkFlipperPlugin) : Interceptor {
    private val android = com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor(networkFlipperPlugin.android)
    override fun intercept(chain: Interceptor.Chain): Response = android.intercept(chain)
}
