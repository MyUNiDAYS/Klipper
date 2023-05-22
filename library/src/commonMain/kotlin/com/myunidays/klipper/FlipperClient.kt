package com.myunidays.klipper


expect class FlipperClient {

    fun addPlugin(plugin: FlipperPlugin)
    fun getPlugin(id: String) : FlipperPlugin?
////    fun getPluginByClass()
//    fun removePlugin(plugin: FlipperPlugin)
    fun start()
    fun stop()
//    fun subscribeForUpdates(stateListener: FlipperStateUpdateListener)
//    fun unsubscribe()
//    fun getState()
//    fun getStateSummary(): StateSummary

    companion object {
        fun getInstance(context: Any? = null) : FlipperClient
    }
}
