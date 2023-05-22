package com.myunidays.klipper


expect class FlipperClient {
    companion object {
        fun getInstance(context: Any? = null) : FlipperClient
    }
}
