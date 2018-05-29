package com.undcover.architecture.common.base;

import android.app.Application;

import com.undcover.architecture.common.SmartSdkManager;
import com.undcover.architecture.common.utils.SmartLog;

public class BaseApp extends Application {
    protected final String TAG = getClass().getSimpleName();
    private static BaseApp application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        SmartSdkManager.init(this);
        SmartSdkManager.setDebug(true);
        initUtils();
    }

    private void initUtils() {
        SmartLog.setLogLevel(SmartLog.LEVEL_D).setDefaultTag("Architecture");
    }

    public static BaseApp getApplication() {
        return application;
    }
}
