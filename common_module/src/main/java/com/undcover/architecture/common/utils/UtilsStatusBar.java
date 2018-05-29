
/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.utils;

import android.content.Context;

import com.undcover.architecture.common.SmartSdkManager;

import java.lang.reflect.Field;

public class UtilsStatusBar {

    public static int getStatusBarHeight() {
        return getStatusBarHeight(SmartSdkManager.getApplication());
    }

    // 获取通知栏宽度
    private static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            SmartLog.e(e.getMessage());
        }
        return statusBarHeight;
    }

}
