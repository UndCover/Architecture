
/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.WindowManager;

import com.undcover.architecture.common.SmartSdkManager;

//import android.content.Context;

public class UtilsScreen {
    private static Point mSize;
    private static int sScreenWidth;
    private static Pair<Integer, Integer> mPair;
    private static int mScreenHeight;

    @SuppressLint("NewApi")
    public synchronized static Point getScreenSizeSDK13() {
        return getScreenSizeSDK13(SmartSdkManager.getApplication());
    }

    @SuppressLint("NewApi")
    public synchronized static Point getScreenSizeSDK13(Context context) {
        if (mSize == null) {
            mSize = new Point();
            if (Build.VERSION.SDK_INT >= 13) {
                WindowManager manager = (WindowManager) context
                        .getSystemService(Context.WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                display.getSize(mSize);
            }
        }
        return mSize;
    }

    public static Pair<Integer, Integer> getScreenSize() {
        return getScreenSize(SmartSdkManager.getApplication());
    }

    private synchronized static Pair<Integer, Integer> getScreenSize(Context context) {
        if (mPair == null) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            mPair = Pair.create(dm.widthPixels, dm.heightPixels);
        }
        return mPair;
    }

    public static DisplayMetrics getDisplayMetrics() {
        return new DisplayMetrics();
    }

//    private synchronized static int getScreenWidth(Activity context) {
//        // get current screen width
//        if (sScreenWidth == 0) {
//            DisplayMetrics dm = new DisplayMetrics();
//            context.getWindowManager().getDefaultDisplay().getMetrics(dm);
//            sScreenWidth = dm.widthPixels;
//        }
//        return sScreenWidth;
//    }
//
//    private synchronized static int getScreenHeight(Activity context) {
//        // get current screen width
//        if (mScreenHeight == 0) {
//            DisplayMetrics dm = new DisplayMetrics();
//            context.getWindowManager().getDefaultDisplay().getMetrics(dm);
//            mScreenHeight = dm.heightPixels;
//        }
//        return mScreenHeight;
//    }

    public static int getScreenWidth() {
        return getScreenWidth(SmartSdkManager.getApplication());
    }

    private synchronized static int getScreenWidth(Context context) {
        // get current screen width
        if (sScreenWidth == 0) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            sScreenWidth = dm.widthPixels;
        }
        return sScreenWidth;
    }

    public static int getScreenHeight() {
        return getScreenHeight(SmartSdkManager.getApplication());
    }

    public synchronized static int getScreenHeight(Context context) {
        if (mScreenHeight == 0) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            mScreenHeight = dm.heightPixels;
        }
        return mScreenHeight;
    }

    public static int dip2px(float dipValue) {
        return dip2px(SmartSdkManager.getApplication(), dipValue);
    }

    private static int dip2px(Context context, float dipValue) {
        float m = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * m + 0.5f);
    }

    public static int px2dip(float pxValue) {
        return px2dip(SmartSdkManager.getApplication(), pxValue);
    }

    private static int px2dip(Context context, float pxValue) {
        float m = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / m + 0.5f);
    }

    public static int sp2px(float spValue) {
        return sp2px(SmartSdkManager.getApplication(), spValue);
    }

    private static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static float getDenisity() {
        return getDensity(SmartSdkManager.getApplication());
    }

    private static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
}
