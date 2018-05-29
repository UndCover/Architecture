/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import com.undcover.architecture.common.utils.SmartLog;

import java.util.Stack;

/**
 * 用于管理应用的Activity<br/>
 * Created by UndCover on 11/24/17.
 */

public class AtyManager {
    private static final String TAG = "AtyManager";
    private static Stack<Activity> atyStack;
    private static AtyManager instance;

    private AtyManager() {
    }

    /**
     * 单实例 , UI无需考虑多线程同步问题
     */
    public static AtyManager getInstance() {
        if (instance == null) {
            instance = new AtyManager();
        }
        return instance;
    }

    /**
     * 添加Activity到栈
     */
    public void add(Activity activity) {
        if (atyStack == null) {
            atyStack = new Stack<>();
        }
        atyStack.add(activity);
        SmartLog.am(TAG, "add " + activity.getClass().getSimpleName() + " stack size:" + atyStack.size());
    }

    /**
     * 将activity移出栈
     *
     * @param activity
     */
    public void remove(Activity activity) {
        if (activity != null && atyStack != null && !atyStack.isEmpty()) {
            atyStack.remove(activity);
            SmartLog.am(TAG, "remove " + activity.getClass().getSimpleName() + " stack size:" + atyStack.size());
        }
    }

    /**
     * 获取栈顶Activity
     */
    public Activity getTop() {
        if (atyStack == null || atyStack.isEmpty()) {
            return null;
        }
        Activity activity = atyStack.lastElement();
        return activity;
    }

    /**
     * 获取指定Activity
     */
    public Activity get(Class<?> cls) {
        Activity activity = null;
        for (Activity aty : atyStack) {
            if (aty.getClass().equals(cls)) {
                activity = aty;
                break;
            }
        }
        return activity;
    }

    /**
     * 结束栈顶Activity
     */
    public void finishTop() {
        Activity activity = atyStack.lastElement();
        finish(activity);
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finish(Activity activity) {
        if (activity != null && atyStack != null && !atyStack.isEmpty()) {
            // 为与系统Activity栈保持一致，且考虑到手机设置项里的"不保留活动"选项引起的Activity生命周期调用onDestroy()方法所带来的问题,此处需要作出如下修正
            if (activity.isFinishing()) {
                atyStack.remove(activity);
                //activity.finish();
            } else if (!activity.isDestroyed()) {
                atyStack.remove(activity);
                activity.finish();
            }
            SmartLog.am(TAG, "finish " + activity.getClass().getSimpleName() + " stack size:" + atyStack.size());
            activity = null;
        }
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finish(Class<?> cls) {
        for (Activity aty : atyStack) {
            if (aty.getClass().equals(cls)) {
                finish(aty);
            }
        }
    }

    /**
     * 关闭除了指定activity以外的全部activity 如果cls不存在于栈中，则栈全部清空
     *
     * @param cls
     */
    public void finishExcept(Class<?> cls) {
        for (Activity aty : atyStack) {
            if (!(aty.getClass().equals(cls))) {
                finish(aty);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAll() {
        for (Activity aty : atyStack) {
            if (null != aty) {
                aty.finish();
            }
        }
        atyStack.clear();
        SmartLog.am(TAG, "finishAll stack size:" + atyStack.size());
    }

    /**
     * 应用程序退出
     */
    public void exit(Context context) {
        try {
            finishAll();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            System.exit(0);
        }
    }
}