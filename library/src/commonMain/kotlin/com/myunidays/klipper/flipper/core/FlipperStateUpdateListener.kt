package com.myunidays.klipper.flipper.core

interface FlipperStateUpdateListener {
    fun onUpdate()
}

//public interface FlipperStateUpdateListener {
//
//  /**
//   * Called when the state of the Flipper client changes. Typical implementations will subscribe by
//   * calling {@link com.facebook.flipper.core.FlipperClient#subscribeForUpdates()}, to start
//   * receiving update events. Calling {@link com.facebook.flipper.core.FlipperClient#getState()}
//   * will retrieve the updated state.
//   */
//  void onUpdate();
//}
