
/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.property;

import com.undcover.architecture.common.utils.UtilsObject;

public class GetSetter<T> extends Getter<T> {

    public GetSetter(T defValue) {
        super(defValue);
    }

    protected void onChange(T newValue) {

    }

    public void set(T value) {
        inited = true;
        if (!UtilsObject.equal(this.value, value)) {
            this.value = value;
            onChange(this.value);
        }
    }
}
