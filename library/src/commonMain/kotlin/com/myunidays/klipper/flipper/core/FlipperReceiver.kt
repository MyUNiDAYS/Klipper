package com.myunidays.klipper.flipper.core

interface FlipperReceiver {
    fun onReceive(params: FlipperObject, responder: FlipperResponder)
}

//public interface FlipperReceiver {
//
//  /**
//   * Reciver for a request sent from the Flipper desktop client.
//   *
//   * @param params Optional set of parameters sent with the request.
//   * @param responder Responder for request, ensure to respond otherwise request will time out on
//   *     the desktop side
//   */
//  void onReceive(FlipperObject params, FlipperResponder responder) throws Exception;
//}
