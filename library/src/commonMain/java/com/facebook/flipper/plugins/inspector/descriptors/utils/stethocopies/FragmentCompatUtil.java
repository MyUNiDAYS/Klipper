///*
// * Copyright (c) Meta Platforms, Inc. and affiliates.
// *
// * This source code is licensed under the MIT license found in the
// * LICENSE file in the root directory of this source tree.
// */
//
//package com.facebook.flipper.plugins.inspector.descriptors.utils.stethocopies;
//
//import android.app.Activity;
//import android.util.Log;
//import android.view.View;
//import java.util.Collections;
//import java.util.List;
//import javax.annotation.Nullable;
//
//public final class FragmentCompatUtil {
//  private static final String TAG = "FragmentCompatUtil";
//
//  private FragmentCompatUtil() {}
//
//  public static boolean isDialogFragment(Object fragment) {
//    FragmentCompat supportLib = FragmentCompat.getSupportLibInstance();
//    if (supportLib != null && supportLib.getDialogFragmentClass().isInstance(fragment)) {
//      return true;
//    }
//
//    FragmentCompat framework = FragmentCompat.getFrameworkInstance();
//    if (framework != null && framework.getDialogFragmentClass().isInstance(fragment)) {
//      return true;
//    }
//
//    return false;
//  }
//
//  @Nullable
//  public static Object findFragmentForView(View view) {
//    Activity activity = ViewUtil.tryGetActivity(view);
//    if (activity == null) {
//      return null;
//    }
//
//    return findFragmentForViewInActivity(activity, view);
//  }
//
//  @Nullable
//  private static Object findFragmentForViewInActivity(Activity activity, View view) {
//    FragmentCompat supportLib = FragmentCompat.getSupportLibInstance();
//
//    // Try the support library version if it is present and the activity is FragmentActivity.
//    if (supportLib != null && supportLib.getFragmentActivityClass().isInstance(activity)) {
//      Object fragment = findFragmentForViewInActivity(supportLib, activity, view);
//      if (fragment != null) {
//        return fragment;
//      }
//    }
//
//    // Try the actual Android runtime version if we are on a sufficiently high API level for it to
//    // exist.  Note that technically we can have both the support library and the framework
//    // version in the same object instance due to FragmentActivity extending Activity (which has
//    // fragment support in the system).
//    FragmentCompat framework = FragmentCompat.getFrameworkInstance();
//    if (framework != null) {
//      Object fragment = findFragmentForViewInActivity(framework, activity, view);
//      if (fragment != null) {
//        return fragment;
//      }
//    }
//
//    return null;
//  }
//
//  private static Object findFragmentForViewInActivity(
//      FragmentCompat compat, Activity activity, View view) {
//    Object fragmentManager = compat.forFragmentActivity().getFragmentManager(activity);
//    if (fragmentManager != null) {
//      return findFragmentForViewInFragmentManager(compat, fragmentManager, view);
//    } else {
//      return null;
//    }
//  }
//
//  @Nullable
//  private static Object findFragmentForViewInFragmentManager(
//      FragmentCompat compat, Object fragmentManager, View view) {
//    List<?> fragments;
//    try {
//      fragments = compat.forFragmentManager().getAddedFragments(fragmentManager);
//    } catch (Exception e) {
//      fragments = Collections.emptyList();
//      Log.e(TAG, "Failed to obtain list of fragments.", e);
//    }
//
//    if (fragments != null) {
//      for (int i = 0, N = fragments.size(); i < N; ++i) {
//        Object fragment = fragments.get(i);
//        Object result = findFragmentForViewInFragment(compat, fragment, view);
//        if (result != null) {
//          return result;
//        }
//      }
//    }
//
//    return null;
//  }
//
//  @Nullable
//  private static Object findFragmentForViewInFragment(
//      FragmentCompat compat, Object fragment, View view) {
//    FragmentAccessor accessor = compat.forFragment();
//
//    if (accessor.getView(fragment) == view) {
//      return fragment;
//    }
//
//    Object childFragmentManager = accessor.getChildFragmentManager(fragment);
//    if (childFragmentManager != null) {
//      return findFragmentForViewInFragmentManager(compat, childFragmentManager, view);
//    }
//
//    return null;
//  }
//}
