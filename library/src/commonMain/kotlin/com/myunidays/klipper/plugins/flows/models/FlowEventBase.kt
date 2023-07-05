package com.myunidays.klipper.plugins.flows.models


interface FlowEventBase {
    val id: String
    val name: String
    val event: FlowEventEnum
    fun toMap(): Map<Any?, *>
}
