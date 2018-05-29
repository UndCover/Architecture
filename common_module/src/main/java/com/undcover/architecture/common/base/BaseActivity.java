/*
 * Create by UndCover on 12/4/17 7:14 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.undcover.architecture.common.R;
import com.undcover.architecture.common.support.Switcher;
import com.undcover.architecture.common.utils.SmartLog;
import com.undcover.architecture.common.utils.SmartToast;

public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG = this.getClass().getSimpleName();

    public void navigateTo(String path) {
        navigateTo(path, null);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SmartLog.lc(TAG, "onCreate");
    }

    /**
     * 获取路由地址
     *
     * @param path
     * @return
     */
    public Uri getRouteUri(String path) {
        String newPath;
        if (path.startsWith("/")) {
            newPath = path.substring(1, path.length());
        } else {
            newPath = path;
        }
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(getResources().getString(R.string.route_scheme));
        builder.authority(getResources().getString(R.string.route_host));
        builder.appendEncodedPath(newPath);
        return builder.build();
    }

    public void navigateTo(Uri uri) {
        ARouter.getInstance()
                .build(uri)
                .withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                .navigation(this);
    }

    public void navigateTo(String path, String group) {
        Postcard postcard;
        if (group == null || group.isEmpty()) {
            postcard = ARouter.getInstance().build(path);
        } else {
            postcard = ARouter.getInstance().build(path, group);
        }
        doAnimation(postcard);
    }

    public void doAnimation(Postcard postcard) {
        if (postcard != null) {
            postcard.withTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    .navigation(this);
        } else {
            SmartToast.showCancelableToast("Postcard is null !");
        }
    }

    public void jumpBack() {
        this.finish();
        this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void jumpTo(Class clazz) {
        Intent intent = new Intent(this, clazz);
        jumpTo(clazz, intent);
    }

    public void jumpTo(Class clazz, Intent intent) {
        Switcher.switchToActivity(this, intent);
    }

    public void jumpToAndFinishItself(Class clazz) {
        Intent intent = new Intent(this, clazz);
        jumpToAndFinishItself(clazz, intent);
    }

    public void jumpToAndFinishItself(Class clazz, Intent intent) {
        Switcher.switchToActivityAndFinishItself(this, intent);
    }

//    public void jumpSingleTop(Class clazz, Intent intent) {
//        jumpToAndFinishItself(clazz, intent);
//        AtyManager.getInstance().finishExcept(clazz);
//    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        jumpBack();
    }
}
