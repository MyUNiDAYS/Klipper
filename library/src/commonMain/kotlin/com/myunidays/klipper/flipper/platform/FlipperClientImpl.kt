package com.myunidays.klipper.flipper.platform

import com.myunidays.klipper.flipper.core.FlipperClient
import com.myunidays.klipper.flipper.core.FlipperPlugin
import com.myunidays.klipper.flipper.core.FlipperStateUpdateListener
import com.myunidays.klipper.flipper.core.StateSummary

class FlipperClientImpl : FlipperClient {
    override fun addPlugin(plugin: FlipperPlugin) {
        TODO("Not yet implemented")
    }

    override fun getPlugin(id: String): FlipperPlugin {
        TODO("Not yet implemented")
    }

    override fun removePlugin(plugin: FlipperPlugin) {
        TODO("Not yet implemented")
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

    override fun subscribeForUpdates(stateListener: FlipperStateUpdateListener) {
        TODO("Not yet implemented")
    }

    override fun unsubscribe() {
        TODO("Not yet implemented")
    }

    override fun getState() {
        TODO("Not yet implemented")
    }

    override fun getStateSummary(): StateSummary {
        TODO("Not yet implemented")
    }
}

//class FlipperClientImpl implements FlipperClient {
//  static {
//    if (BuildConfig.IS_INTERNAL_BUILD || BuildConfig.LOAD_FLIPPER_EXPLICIT) {
//      SoLoader.loadLibrary("flipper");
//    }
//  }
//
//  private final HybridData mHybridData;
//  private final Map<Class<?>, String> mClassIdentifierMap = new HashMap<>(8);
//
//  private FlipperClientImpl(HybridData hd) {
//    mHybridData = hd;
//  }
//
//  public static native void init(
//      EventBase callbackWorker,
//      EventBase connectionWorker,
//      int insecurePort,
//      int securePort,
//      int altInsecurePort,
//      int altSecurePort,
//      String host,
//      String os,
//      String device,
//      String deviceId,
//      String app,
//      String appId,
//      String privateAppDirectory);
//
//  public static native FlipperClientImpl getInstance();
//
//  @Override
//  public void addPlugin(FlipperPlugin plugin) {
//    mClassIdentifierMap.put(plugin.getClass(), plugin.getId());
//    addPluginNative(plugin);
//  }
//
//  public native void addPluginNative(FlipperPlugin plugin);
//
//  /**
//   * @deprecated Prefer using {@link #getPluginByClass(Class)} over the stringly-typed interface.
//   */
//  @Override
//  @Nullable
//  @Deprecated
//  public native <T extends FlipperPlugin> T getPlugin(String id);
//
//  @Nullable
//  @Override
//  public <T extends FlipperPlugin> T getPluginByClass(Class<T> cls) {
//    final String id = mClassIdentifierMap.get(cls);
//    //noinspection deprecation
//    return getPlugin(id);
//  }
//
//  public native void removePluginNative(FlipperPlugin plugin);
//
//  @Override
//  public void removePlugin(FlipperPlugin plugin) {
//    mClassIdentifierMap.remove(plugin.getClass());
//    removePluginNative(plugin);
//  }
//
//  @Override
//  public native void start();
//
//  @Override
//  public native void stop();
//
//  @Override
//  public native void subscribeForUpdates(FlipperStateUpdateListener stateListener);
//
//  @Override
//  public native void unsubscribe();
//
//  @Override
//  public native String getState();
//
//  @Override
//  public native StateSummary getStateSummary();
//}
