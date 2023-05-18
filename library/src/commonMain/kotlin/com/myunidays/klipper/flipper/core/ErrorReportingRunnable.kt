package com.myunidays.klipper.flipper.core

abstract class ErrorReportingRunnable(mConnection: FlipperConnection) {
}

//public abstract class ErrorReportingRunnable implements Runnable {
//
//  private final FlipperConnection mConnection;
//
//  public ErrorReportingRunnable(FlipperConnection connection) {
//    mConnection = connection;
//  }
//
//  @Override
//  public final void run() {
//    try {
//      runOrThrow();
//    } catch (Throwable e) {
//      if (mConnection != null) {
//        StringWriter sw = new StringWriter();
//        PrintWriter pw = new PrintWriter(sw);
//        e.printStackTrace(pw);
//        String sStackTrace = sw.toString(); // stack trace as a string
//        mConnection.reportErrorWithMetadata(e.toString(), sStackTrace);
//      }
//    } finally {
//      doFinally();
//    }
//  }
//
//  protected void doFinally() {}
//
//  protected abstract void runOrThrow() throws Exception;
//}
