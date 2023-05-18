package com.myunidays.klipper.common.memory.manager

interface DebugMemoryManager {
    fun trimMemory(trimType: Int)
    companion object {
        const val ON_CLOSE_TO_DALVIK_HEAP_LIMIT = 1
        const val ON_SYSTEM_LOW_MEMORY_WHILE_APP_IN_FOREGROUND = 2
        const val ON_SYSTEM_LOW_MEMORY_WHILE_APP_IN_BACKGROUND = 3
        const val ON_APP_BACKGROUNDED = 4
    }
}