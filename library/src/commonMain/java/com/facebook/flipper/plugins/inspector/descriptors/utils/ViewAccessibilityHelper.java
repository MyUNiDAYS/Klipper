///*
// * Copyright (c) Meta Platforms, Inc. and affiliates.
// *
// * This source code is licensed under the MIT license found in the
// * LICENSE file in the root directory of this source tree.
// */
//
//package com.facebook.flipper.plugins.inspector.descriptors.utils;
//
//import android.view.View;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
//import javax.annotation.Nullable;
//
///** Class that helps with accessibility by providing useful methods. */
//public final class ViewAccessibilityHelper {
//
//  /**
//   * Creates and returns an {@link AccessibilityNodeInfoCompat} from the the provided {@link View}.
//   * Note: This does not handle recycling of the {@link AccessibilityNodeInfoCompat}.
//   *
//   * @param view The {@link View} to create the {@link AccessibilityNodeInfoCompat} from.
//   * @return {@link AccessibilityNodeInfoCompat}
//   */
//  @Nullable
//  public static AccessibilityNodeInfoCompat createNodeInfoFromView(View view) {
//    if (view == null) {
//      return null;
//    }
//
//    final AccessibilityNodeInfoCompat nodeInfo = AccessibilityNodeInfoCompat.obtain();
//
//    // For some unknown reason, Android seems to occasionally throw a NPE from
//    // onInitializeAccessibilityNodeInfo.
//    try {
//      ViewCompat.onInitializeAccessibilityNodeInfo(view, nodeInfo);
//    } catch (NullPointerException e) {
//      if (nodeInfo != null) {
//        nodeInfo.recycle();
//      }
//      return null;
//    }
//
//    return nodeInfo;
//  }
//}
