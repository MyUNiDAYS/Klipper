package com.myunidays.klipper.flipper.core

interface FlipperSocket {
    fun flipperConnect()
    fun flipperDisconnect()
    fun flipperSend(message: String)
    fun flipperSetEventHandler(eventHandler: FlipperSocketEventHandler)
    companion object {
        const val SOCKET_TAG = 0x000090000
    }
}

//public interface FlipperSocket {
//
//  /** The value used by Flipper to tag sockets, visible to {@link android.net.TrafficStats}. */
//  int SOCKET_TAG = 0x000090000;
//
//  /** Connect to the endpoint. */
//  void flipperConnect();
//
//  /** Disconnect from the endpoint. */
//  void flipperDisconnect();
//
//  /**
//   * Call a remote method on the Flipper desktop application, passing an optional JSON array as a
//   * parameter.
//   */
//  void flipperSend(String message);
//
//  /** Sets a socket event handler. */
//  void flipperSetEventHandler(FlipperSocketEventHandler eventHandler);
//}
