
/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.property;

public class Getter<T> {
    protected T value = null;
    private T defValue = null;
    protected volatile boolean inited = false;

    public Getter(T defValue) {
        this.defValue = defValue;
    }

    public void reset() {
        value = null;
        inited = false;
    }

    protected T onInit(T defValue) {
        return defValue;
    }

    public T get() {
        return get(defValue);
    }

    public T get(T defValue) {
        if (!inited) {
            inited = true;
            value = onInit(defValue);
        }
        return this.value;
    }
}
