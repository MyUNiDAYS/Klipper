package com.myunidays.klipper.flipper.plugins.common

import com.myunidays.klipper.flipper.core.FlipperObject
import com.myunidays.klipper.flipper.core.FlipperReceiver
import com.myunidays.klipper.flipper.core.FlipperResponder

abstract class MainThreadFlipperReceiver : FlipperReceiver {
    //private val mHandler: Handler
    override fun onReceive(params: FlipperObject, responder: FlipperResponder) {
//        mHandler.post(
//        new Runnable() {
//          @Override
//          public void run() {
//            try {
//              onReceiveOnMainThread(params, responder);
//            } catch (Exception ex) {
//              responder.error(
//                  new FlipperObject.Builder()
//                      .put("name", ex.getClass().getCanonicalName())
//                      .put("message", ex.getMessage())
//                      .put("stacktrace", getStackTraceString(ex))
//                      .build());
//            }
//          }
//        });
    }
    fun getStackTraceString(th: Throwable): String =
        th.stackTraceToString()

    abstract fun onReceiveOnMainThread(params: FlipperObject, responder: FlipperResponder)
}

//public abstract class MainThreadFlipperReceiver implements FlipperReceiver {
//
//  private final Handler mHandler = new Handler(Looper.getMainLooper());
//
//  @Override
//  public final void onReceive(final FlipperObject params, final FlipperResponder responder) {
//    mHandler.post(
//        new Runnable() {
//          @Override
//          public void run() {
//            try {
//              onReceiveOnMainThread(params, responder);
//            } catch (Exception ex) {
//              responder.error(
//                  new FlipperObject.Builder()
//                      .put("name", ex.getClass().getCanonicalName())
//                      .put("message", ex.getMessage())
//                      .put("stacktrace", getStackTraceString(ex))
//                      .build());
//            }
//          }
//        });
//  }
//
//  private static String getStackTraceString(Throwable th) {
//    StringWriter stringWriter = new StringWriter();
//    th.printStackTrace(new PrintWriter(stringWriter));
//    return stringWriter.toString();
//  }
//
//  public abstract void onReceiveOnMainThread(FlipperObject params, FlipperResponder responder)
//      throws Exception;
//}
