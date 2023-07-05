package com.myunidays.klipper.plugins.flows

import cocoapods.FlipperKit.FlipperConnectionProtocol
import com.myunidays.klipper.FlipperPlugin
import com.myunidays.klipper.plugins.flows.models.FlowEvent
import com.myunidays.klipper.plugins.flows.models.FlowEventBase
import com.myunidays.klipper.plugins.flows.models.FlowEventEnum
import com.myunidays.klipper.plugins.flows.models.FlowEventValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import platform.Foundation.NSUUID
import platform.darwin.NSObject

actual class FlowPlugin : FlipperPlugin, NSObject() {
    var connection: FlipperConnectionProtocol? = null
    override fun runInBackground(): Boolean = true
    override fun didConnect(connection: FlipperConnectionProtocol?) {
        this.connection = connection
    }

    override fun didDisconnect() {
        connection = null
    }

    override fun identifier(): String = flowPluginId
    actual fun sendData(flowData: FlowEventBase) {
        connection?.send(
            flowPluginEventMethod,
            withParams = flowData.toMap()
        )
    }

    actual fun addFlow(flow: Flow<Any?>) {
        CoroutineScope(Dispatchers.Default).launch {
            flow.onStart {
                sendData(
                    FlowEvent(
                        id = createEventID(),
                        name = flow.toString(),
                        event = FlowEventEnum.START
                    )
                )
            }
        }
        CoroutineScope(Dispatchers.Default).launch {
            flow.catch {
                sendData(
                    FlowEvent(
                        id = createEventID(),
                        name = flow.toString(),
                        event = FlowEventEnum.ERROR
                    )
                )
            }
        }
        CoroutineScope(Dispatchers.Default).launch {
            flow.collectIndexed { index, value ->
                sendData(
                    FlowEventValue(
                        id = createEventID(),
                        name = flow.toString(),
                        event = FlowEventEnum.NEXT,
                        index = index,
                        value = value.toString()
                    )
                )
            }
        }
        CoroutineScope(Dispatchers.Default).launch {
            flow.onCompletion {
                sendData(
                    FlowEvent(
                        id = createEventID(),
                        name = flow.toString(),
                        event = FlowEventEnum.COMPLETE
                    )
                )
            }
        }
    }
}

actual fun createEventID(): String = NSUUID().UUIDString