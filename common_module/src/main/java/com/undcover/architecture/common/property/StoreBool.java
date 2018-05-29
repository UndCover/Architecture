
/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.property;

import android.content.SharedPreferences;

public class StoreBool extends GetSetter<Boolean> {
    private String key;
    private SharedPreferences sp;

    public StoreBool(SharedPreferences prefer, String key, boolean defValue) {
        super(defValue);
        this.key = key;
        sp = prefer;
    }

    @Override
    protected Boolean onInit(Boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    @Override
    protected void onChange(Boolean newValue) {
        sp.edit().putBoolean(key, newValue).commit();
    }
}
