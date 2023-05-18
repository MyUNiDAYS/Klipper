package com.myunidays.klipper.flipper.core

interface FlipperClient {
    fun addPlugin(plugin: FlipperPlugin)
    fun getPlugin(id: String) : FlipperPlugin
//    fun getPluginByClass()
    fun removePlugin(plugin: FlipperPlugin)
    fun start()
    fun stop()
    fun subscribeForUpdates(stateListener: FlipperStateUpdateListener)
    fun unsubscribe()
    fun getState()
    fun getStateSummary(): StateSummary
}

//public interface FlipperClient {
//  void addPlugin(FlipperPlugin plugin);
//
//  @Nullable
//  <T extends FlipperPlugin> T getPlugin(String id);
//
//  @Nullable
//  <T extends FlipperPlugin> T getPluginByClass(Class<T> cls);
//
//  void removePlugin(FlipperPlugin plugin);
//
//  void start();
//
//  void stop();
//
//  void subscribeForUpdates(FlipperStateUpdateListener stateListener);
//
//  void unsubscribe();
//
//  String getState();
//
//  StateSummary getStateSummary();
//}
