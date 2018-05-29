
/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.property;

import android.content.SharedPreferences;

public class StoreLong extends GetSetter<Long> {
    private String key;
    private SharedPreferences sp;

    public StoreLong(SharedPreferences prefer, String key, long defValue) {
        super(defValue);
        this.key = key;
        sp = prefer;
    }

    @Override
    protected Long onInit(Long defValue) {
        return sp.getLong(key, defValue);
    }

    @Override
    protected void onChange(Long newValue) {
        sp.edit().putLong(key, newValue).commit();
    }
}
