/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.property;

import android.text.TextUtils;

import com.undcover.architecture.common.utils.SmartLog;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class SmartProperty {
    private static final String TAG = SmartProperty.class.getSimpleName();
    private static final String DEFAULT_CONFIG_FILE = "/assets/config.properties";
    private Properties mProperties;
    private static SmartProperty sProperty;

    public static SmartProperty getInstance() {
        if (null == sProperty) {
            sProperty = new SmartProperty();
        }
        return sProperty;
    }

    private SmartProperty() {
        // load(DEFAULT_CONFIG_FILE);
        mProperties = new Properties();
    }

    public void load(String path) {
        if (TextUtils.isEmpty(path)) {
            path = DEFAULT_CONFIG_FILE;
        }
        InputStream in = null;
        try {
            in = SmartProperty.class.getResourceAsStream(path);
            mProperties.load(in);
        } catch (Exception e) {
            SmartLog.e(TAG, e.getMessage());
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                SmartLog.e(TAG, e.getMessage());
            }
        }
    }

    public String getParameter(String key) {
        String para = null;
        if (null != mProperties) {
            para = mProperties.getProperty(key);
            if (null != para) {
                para = para.trim();
            }
        }
        return para;
    }

    public String getParameter(String key, String defaultValue) {
        String para = null;
        if (null != mProperties) {
            para = mProperties.getProperty(key, defaultValue);
            if (null != para) {
                para = para.trim();
            }
        }
        return para;
    }
}
