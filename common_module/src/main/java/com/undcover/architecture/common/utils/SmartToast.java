/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.utils;

import android.widget.Toast;

import com.undcover.architecture.common.SmartSdkManager;


/**
 * Created by UndCover on 16/9/7.
 */
public class SmartToast {
    private static Toast innerToast;
    private static SmartToast util;

    private static SmartToast getInstance() {
        if (util == null) {
            synchronized (SmartToast.class) {
                if (util == null) {
                    util = new SmartToast();
                }
            }
        }
        return util;
    }

    private static void checkInit() {
        if(util == null){
            getInstance();
        }
    }

    public static void showCancelableToast(String msg) {
        showCancelableToast(msg, Toast.LENGTH_SHORT);
    }

    /**
     * 快速显示Toast,无需排队等待
     *
     * @param msg
     * @param duration
     */
    public static void showCancelableToast(String msg, int duration) {
        checkInit();
        if (innerToast != null) {
            innerToast.cancel();
        }
        innerToast = Toast.makeText(SmartSdkManager.getApplication(), msg, duration);
        innerToast.show();
    }

    public static void showMessage(String msg, int duration) {
        checkInit();
        Toast.makeText(SmartSdkManager.getApplication(), msg, duration).show();
    }
}
