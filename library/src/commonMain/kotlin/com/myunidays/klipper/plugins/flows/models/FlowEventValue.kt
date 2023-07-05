package com.myunidays.klipper.plugins.flows.models

data class FlowEventValue(
    override val id: String,
    override val name: String,
    override val event: FlowEventEnum,
    val index: Int,
    val value: String
) : FlowEventBase {
    override fun toMap(): Map<Any?, Any> = mapOf(
        "id" to id,
        "name" to name,
        "event" to event.name,
        "index" to index,
        "value" to value
    )
}