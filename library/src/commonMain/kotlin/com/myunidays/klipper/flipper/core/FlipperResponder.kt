package com.myunidays.klipper.flipper.core

interface FlipperResponder {
    fun success(response: FlipperObject)
    fun success(response: FlipperArray)
    fun success()
    fun error(response: FlipperObject)
}

//public interface FlipperResponder {
//
//  /** Deliver a successful response to the Flipper desktop app. */
//  void success(FlipperObject response);
//
//  /** Deliver a successful response to the Flipper desktop app. */
//  void success(FlipperArray response);
//
//  /** Deliver a successful response to the Flipper desktop app. */
//  void success();
//
//  /** Inform the Flipper desktop app of an error in handling the request. */
//  void error(FlipperObject response);
//}
