/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common;

import android.content.Context;

public class SmartSdkManager {
    private static final String TAG = SmartSdkManager.class.getSimpleName();

    private static Context context;
    private static boolean debug =true;

    private SmartSdkManager() {
        throw new UnsupportedOperationException("You can't instantiate SmartSdkManager");
    }

    public static void init(Context context) {
        SmartSdkManager.context = context.getApplicationContext();
    }

    public static void setDebug(boolean debug) {
        SmartSdkManager.debug = debug;
    }

    public static boolean isDebug() {
        return debug;
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getApplication() {
        if (context != null) return context;
        throw new NullPointerException("You should execute init() first");
    }
}
