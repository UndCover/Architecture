
/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.property;

import android.content.SharedPreferences;

public class StoreInt extends GetSetter<Integer> {
    private String key;
    private SharedPreferences sp;

    public StoreInt(SharedPreferences prefer, String key, int defValue) {
        super(defValue);
        this.key = key;
        sp = prefer;
    }

    @Override
    protected Integer onInit(Integer defValue) {
        return sp.getInt(key, defValue);
    }

    @Override
    protected void onChange(Integer newValue) {
        sp.edit().putInt(key, newValue).commit();
    }
}
