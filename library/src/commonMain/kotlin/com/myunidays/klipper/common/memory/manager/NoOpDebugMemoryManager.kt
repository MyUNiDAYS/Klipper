package com.myunidays.klipper.common.memory.manager

class NoOpDebugMemoryManager : DebugMemoryManager {
    override fun trimMemory(trimType: Int) {}
}
