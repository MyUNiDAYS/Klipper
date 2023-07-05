package com.myunidays.klipper.plugins.flows.models

data class FlowEvent(
    override val id: String,
    override val name: String,
    override val event: FlowEventEnum
) : FlowEventBase {
    override fun toMap(): Map<Any?, Any> = mapOf(
        "id" to id,
        "name" to name,
        "event" to event.name
    )
}