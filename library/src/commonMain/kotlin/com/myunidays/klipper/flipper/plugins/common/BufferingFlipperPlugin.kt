package com.myunidays.klipper.flipper.plugins.common

import com.myunidays.klipper.flipper.core.FlipperConnection
import com.myunidays.klipper.flipper.core.FlipperObject
import com.myunidays.klipper.flipper.core.FlipperPlugin

abstract class BufferingFlipperPlugin: FlipperPlugin {
//    private var mEventQueue: RingBuffer<CachedFlipperEvent>? = null
    private var mEventQueue: MutableList<CachedFlipperEvent> = mutableListOf()
    private var mConnection: FlipperConnection? = null
    private var mMockResponseConnectionListenerConnectionListener: MockResponseConnectionListener? = null

    fun setConnectionListener(listener: MockResponseConnectionListener) {
        this.mMockResponseConnectionListenerConnectionListener = listener
    }
    fun removeConnectionListener() {
        this.mMockResponseConnectionListenerConnectionListener = null
    }
    fun getConnection() : FlipperConnection? = mConnection
    fun isConnected() = mConnection != null

    private fun sendBufferedEvents() {
        mEventQueue.forEach { event ->
            mConnection?.send(event.method, event.flipperObject)
        }
        mEventQueue.clear()
    }

    override fun onConnect(connection: FlipperConnection) {
        mConnection = connection

        sendBufferedEvents()

        if (mMockResponseConnectionListenerConnectionListener != null) {
            mMockResponseConnectionListenerConnectionListener!!.onConnect(connection)
        }
    }

    override fun onDisconnect() {
        mConnection = null
        if (mMockResponseConnectionListenerConnectionListener != null) {
            mMockResponseConnectionListenerConnectionListener!!.onDisconnect()
        }
    }

    override fun runInBackground(): Boolean = true

    companion object {
        const val BUFFER_SIZE: Int = 500
    }

    data class CachedFlipperEvent(
        val method: String,
        val flipperObject: FlipperObject
    )

    interface MockResponseConnectionListener {
        fun onConnect(connection: FlipperConnection)
        fun onDisconnect()
    }
}

//  private static final int BUFFER_SIZE = 500;
//
//  private @Nullable RingBuffer<CachedFlipperEvent> mEventQueue;
//  private @Nullable FlipperConnection mConnection;
//  private @Nullable MockResponseConnectionListener
//      mMockResponseConnectionListenerConnectionListener;
//
//  public synchronized void setConnectionListener(@Nonnull MockResponseConnectionListener listener) {
//    this.mMockResponseConnectionListenerConnectionListener = listener;
//  }
//
//  public synchronized void removeConnectionListener() {
//    this.mMockResponseConnectionListenerConnectionListener = null;
//  }
//
//  @Override
//  public synchronized void onConnect(FlipperConnection connection) {
//    mConnection = connection;
//
//    sendBufferedEvents();
//
//    if (this.mMockResponseConnectionListenerConnectionListener != null) {
//      this.mMockResponseConnectionListenerConnectionListener.onConnect(connection);
//    }
//  }
//
//  @Override
//  public synchronized void onDisconnect() {
//    mConnection = null;
//
//    if (this.mMockResponseConnectionListenerConnectionListener != null) {
//      this.mMockResponseConnectionListenerConnectionListener.onDisconnect();
//    }
//  }
//
//  @Override
//  public boolean runInBackground() {
//    return true;
//  }
//
//  public synchronized FlipperConnection getConnection() {
//    return mConnection;
//  }
//
//  public synchronized boolean isConnected() {
//    return mConnection != null;
//  }
//
//  public synchronized void send(String method, FlipperObject flipperObject) {
//    if (mEventQueue == null) {
//      mEventQueue = new RingBuffer<>(BUFFER_SIZE);
//    }
//    if (mConnection != null) {
//      mConnection.send(method, flipperObject);
//    } else {
//      mEventQueue.enqueue(new CachedFlipperEvent(method, flipperObject));
//    }
//  }
//
//  private synchronized void sendBufferedEvents() {
//    if (mEventQueue != null && mConnection != null) {
//      for (CachedFlipperEvent cachedFlipperEvent : mEventQueue.asList()) {
//        mConnection.send(cachedFlipperEvent.method, cachedFlipperEvent.flipperObject);
//      }
//      mEventQueue.clear();
//    }
//  }
//
//  private static class CachedFlipperEvent {
//    final String method;
//    final FlipperObject flipperObject;
//
//    private CachedFlipperEvent(String method, FlipperObject flipperObject) {
//      this.method = method;
//      this.flipperObject = flipperObject;
//    }
//  }
//
//  public interface MockResponseConnectionListener {
//    void onConnect(FlipperConnection connection);
//
//    void onDisconnect();
//  }
//}
