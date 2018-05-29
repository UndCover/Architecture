/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.utils;

import com.undcover.architecture.common.SmartSdkManager;

public class UtilsResource {
    public static int getResIdByName(String resName, String resType) {
        return SmartSdkManager.getApplication().getResources().getIdentifier(
                resName, resType, SmartSdkManager.getApplication().getPackageName());
    }

    public static int getDrawableResIdByName(String resName) {
        return getResIdByName(resName, "drawable");
    }

    public static int getStringResIdByName(String resName) {
        return getResIdByName(resName, "string");
    }

    public static int getLayoutResIdByName(String resName) {
        return getResIdByName(resName, "layout");
    }
}
