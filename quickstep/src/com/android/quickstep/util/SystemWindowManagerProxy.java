/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.quickstep.util;

import static android.view.Display.DEFAULT_DISPLAY;

import android.content.Context;
import android.util.ArrayMap;
import android.view.Surface;
import android.view.WindowManager;
import android.view.WindowMetrics;

import com.android.launcher3.Utilities;
import com.android.launcher3.util.WindowBounds;
import com.android.launcher3.util.window.CachedDisplayInfo;
import com.android.launcher3.util.window.WindowManagerProxy;

import java.util.Set;

/**
 * Extension of {@link WindowManagerProxy} with some assumption for the default system Launcher
 */
public class SystemWindowManagerProxy extends WindowManagerProxy {

    public SystemWindowManagerProxy(Context context) {
        super(Utilities.ATLEAST_T);
    }

    @Override
    public int getRotation(Context displayInfoContext) {
        return displayInfoContext.getResources().getConfiguration().windowConfiguration
                .getRotation();
    }

    @Override
    public ArrayMap<CachedDisplayInfo, WindowBounds[]> estimateInternalDisplayBounds(
            Context displayInfoContext) {
        if (!Utilities.ATLEAST_T) {
            return super.estimateInternalDisplayBounds(displayInfoContext);
        }
        ArrayMap<CachedDisplayInfo, WindowBounds[]> result = new ArrayMap<>();
        WindowManager windowManager = displayInfoContext.getSystemService(WindowManager.class);
        Set<WindowMetrics> possibleMaximumWindowMetrics =
                windowManager.getPossibleMaximumWindowMetrics(DEFAULT_DISPLAY);
        for (WindowMetrics windowMetrics : possibleMaximumWindowMetrics) {
            CachedDisplayInfo info = getDisplayInfo(windowMetrics, Surface.ROTATION_0);
            WindowBounds[] bounds = estimateWindowBounds(displayInfoContext, info);
            result.put(info, bounds);
        }
        return result;
    }
}
