package com.myunidays.klipper.flipper.plugins.common

class RingBuffer<T>(capacity: Int) {
    /**
     * Indicates if buffer is full, should be checked before [write] operation
     */
    var isFull = false
        private set

    /**
     * Indicates if buffer is empty, should be checked before [read] operation
     */
    var isEmpty = true
        private set

    private val buffer = arrayOfNulls<Any>(capacity)

    private var readPos = 0
    private var writePos = 0

    /**
     * Obtains the most recent element from buffer
     */
    fun read(): T {
        if (isEmpty) throw IllegalStateException("buffer is empty, nothing to read")

        @Suppress("UNCHECKED_CAST")
        val value: T = buffer[readPos] as T
        // this step is not required, but is good for illustrating movement of values
        buffer[readPos] = null

        // move read position one step futher
        readPos = readPos.incrementPointerPos()
        // if read pos and write pos are matching - it means we've read everything - buffer is empty
        isEmpty = readPos == writePos
        // buffer can't be full if we've read anythign
        isFull = false
        return value
    }

    fun write(value: T) {
        if (isFull) throw IllegalStateException("buffer is full, no room to write")

        buffer[writePos] = value
        // move read position one step futher
        writePos = writePos.incrementPointerPos()
        // if read pos and write pos are matching - it means we've wrote to the last cell - buffer is full
        isFull = readPos == writePos
        // buffer can't be empty after write
        isEmpty = false
        printContents()
    }

    /**
     * For debug purposes
     */
    fun printContents() = println(buffer)


    // increments pointer value cycling to 0 if it reaches buffer size
    private fun Int.incrementPointerPos() = (this + 1) % buffer.size
}