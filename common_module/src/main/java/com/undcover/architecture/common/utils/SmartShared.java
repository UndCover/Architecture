
/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.undcover.architecture.common.SmartSdkManager;

public class SmartShared {
    public static final String SHARED = "smart_shared";

    public static void setShared(String key, String value) {
        setShared(SmartSdkManager.getApplication(), key, value);
    }

    public static String getShared(String key) {
        return getShared(SmartSdkManager.getApplication(), key);
    }

    public static void setShared(String key, int value) {
        setShared(SmartSdkManager.getApplication(), key, value);
    }

    public static int getIntShared(String key) {
        return getIntShared(SmartSdkManager.getApplication(), key);
    }

    public static void setShared(String key, boolean value) {
        setShared(SmartSdkManager.getApplication(), key, value);
    }

    public static boolean getBooleanShared(String key, boolean def) {
        return getBooleanShared(SmartSdkManager.getApplication(), key, def);
    }

    public static void setShared(String key, String value, String filename) {
        setShared(SmartSdkManager.getApplication(), key, value, filename);
    }

    public static String getShared(String key, String filename) {
        return getShared(SmartSdkManager.getApplication(), key, filename);
    }

    public static boolean clearDatas(String filename) {
        return clearDatas(SmartSdkManager.getApplication(), filename);
    }

    public static void setShared(Context context, String key, String value) {
        SharedPreferences shared = context.getSharedPreferences(SHARED, Context.MODE_PRIVATE);
        shared.edit().putString(key, value).commit();
    }

    public static String getShared(Context context, String key) {
        SharedPreferences shared = context.getSharedPreferences(SHARED, Context.MODE_PRIVATE);
        return shared.getString(key, null);
    }

    public static void setShared(Context context, String key, int value) {
        SharedPreferences shared = context.getSharedPreferences(SHARED, Context.MODE_PRIVATE);
        shared.edit().putInt(key, value).commit();
    }

    public static int getIntShared(Context context, String key) {
        SharedPreferences shared = context.getSharedPreferences(SHARED, Context.MODE_PRIVATE);
        return shared.getInt(key, -1);
    }

    public static void setShared(Context context, String key, boolean value) {
        SharedPreferences shared = context.getSharedPreferences(SHARED, Context.MODE_PRIVATE);
        shared.edit().putBoolean(key, value).commit();
    }

    public static boolean getBooleanShared(Context context, String key, boolean def) {
        SharedPreferences shared = context.getSharedPreferences(SHARED, Context.MODE_PRIVATE);
        return shared.getBoolean(key, def);
    }

    public static void setShared(Context context, String key, String value, String filename) {
        SharedPreferences shared = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        shared.edit().putString(key, value).commit();
    }

    public static String getShared(Context context, String key, String filename) {
        SharedPreferences shared = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        return shared.getString(key, null);
    }

    public static boolean clearDatas(Context context, String filename) {
        SharedPreferences shared = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        return shared.edit().clear().commit();
    }
}
