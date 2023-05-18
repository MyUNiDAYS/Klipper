package com.myunidays.klipper.flipper.core

interface FlipperPlugin {
    fun getId(): String
    fun onConnect(connection: FlipperConnection)
    fun onDisconnect()
    fun runInBackground(): Boolean
}

//public interface FlipperPlugin {
//
//  /**
//   * @return The id of this plugin. This is the namespace which Flipper desktop plugins will call
//   *     methods on to route them to your plugin. This should match the id specified in your React
//   *     plugin.
//   */
//  String getId();
//
//  /**
//   * Called when a connection has been established. The connection passed to this method is valid
//   * until {@link FlipperPlugin#onDisconnect()} is called.
//   */
//  void onConnect(FlipperConnection connection) throws Exception;
//
//  /**
//   * Called when the connection passed to {@link FlipperPlugin#onConnect(FlipperConnection)} is no
//   * longer valid. Do not try to use the connection in or after this method has been called.
//   */
//  void onDisconnect() throws Exception;
//
//  /**
//   * Returns true if the plugin is meant to be run in background too, otherwise it returns false.
//   */
//  boolean runInBackground();
//}
