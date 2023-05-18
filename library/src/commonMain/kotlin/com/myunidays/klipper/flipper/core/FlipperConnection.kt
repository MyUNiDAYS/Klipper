package com.myunidays.klipper.flipper.core

interface FlipperConnection {

    fun send(method: String, params: FlipperObject)
    /**
     * Call a remote method on the Flipper desktop application, passing an optional JSON array as a
     * parameter.
     */
    fun send(method: String, params: FlipperArray)

    /**
     * Call a remote method on the Flipper desktop application, passing an optional JSON string as a
     * parameter.
     */
    fun send(method: String, message: String)

    /** Report client error with reason and stacktrace as an argument */
    fun reportErrorWithMetadata(reason: String, stackTrace: String)

    /** Report client error */
    fun reportError(throwable: Throwable)

    /**
     * Register a receiver for a remote method call issued by the Flipper desktop application. The
     * FlipperReceiver is passed a responder to respond back to the desktop application.
     */
    fun receive(method: String, receiver: FlipperReceiver)
}