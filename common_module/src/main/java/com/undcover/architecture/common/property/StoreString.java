
/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.property;

import android.content.SharedPreferences;

public class StoreString extends GetSetter<String> {
    private String key;
    private SharedPreferences sp;

    public StoreString(SharedPreferences prefer, String key, String defValue) {
        super(defValue);
        this.key = key;
        sp = prefer;
    }

    @Override
    protected String onInit(String defValue) {
        return sp.getString(key, defValue);
    }

    @Override
    protected void onChange(String newValue) {
        sp.edit().putString(key, newValue).commit();
    }
}
