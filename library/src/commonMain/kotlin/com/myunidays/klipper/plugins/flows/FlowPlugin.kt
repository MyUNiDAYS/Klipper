package com.myunidays.klipper.plugins.flows

import com.myunidays.klipper.plugins.flows.models.FlowEventBase
import kotlinx.coroutines.flow.Flow

internal const val FLOWPLUGINID = "Kotlin-Flows"
internal const val FLOWPLUGINEVENTMETHOD = "newEvent"

// An Issue with KMM and ios when it comes to NSObject, so there is duplication in the addFlow function
expect class FlowPlugin() {
    fun sendData(flowData: FlowEventBase)

    fun addFlow(flow: Flow<Any?>)
}

expect fun createEventID(): String
