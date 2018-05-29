package com.undcover.architecture.common.support;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;

import com.undcover.architecture.common.R;

/**
 * Created by UndCover on 17/5/5.
 */

public class Switcher {
    private static final int ANIMATION_FADE = 0;
    private static final int ANIMATION_FLIP_FROM_RIGHT = 1;
    private static final int ANIMATION_FLIP_FROM_LEFT = 2;
    private static final int ANIMATION_FLIP_FROM_TOP = 3;
    private static final int ANIMATION_FLIP_FROM_BOTTOM = 4;

    private static Activity sRecentActivity;


    public static final void switchToActivity(Fragment from, Intent to) {
        sRecentActivity = from.getActivity();
        from.startActivity(to);
        doAnimation(from.getActivity(), ANIMATION_FLIP_FROM_RIGHT);
    }

    public static final void switchToActivityWithResult(Fragment from, Intent to, int requestCode) {
        sRecentActivity = from.getActivity();
        from.startActivityForResult(to, requestCode);
        doAnimation(from.getActivity(), ANIMATION_FLIP_FROM_RIGHT);
    }

    public static final void switchToActivityAndFinishItself(Fragment from, Intent to) {
        from.startActivity(to);
        from.getActivity().finish();
        doAnimation(from.getActivity(), ANIMATION_FLIP_FROM_RIGHT);
    }

    public static final void switchBack(Fragment from) {
        from.getActivity().finish();
        doAnimation(from.getActivity(), ANIMATION_FLIP_FROM_LEFT);
    }

    public static final void switchToActivity(Fragment from, Intent to, int animation) {
        sRecentActivity = from.getActivity();
        from.startActivity(to);
        doAnimation(from.getActivity(), animation);
    }

    public static final void switchToActivityWithResult(Fragment from, Intent to, int requestCode, int animation) {
        sRecentActivity = from.getActivity();
        from.startActivityForResult(to, requestCode);
        doAnimation(from.getActivity(), animation);
    }

    public static final void switchToActivityAndFinishItself(Fragment from, Intent to, int animation) {
        from.startActivity(to);
        from.getActivity().finish();
        doAnimation(from.getActivity(), animation);
    }

    public static final void switchBack(Fragment from, int animation) {
        from.getActivity().finish();
        doAnimation(from.getActivity(), animation);
    }

    public static final void switchToActivity(android.support.v4.app.Fragment from, Intent to) {
        sRecentActivity = from.getActivity();
        from.startActivity(to);
        doAnimation(from.getActivity(), ANIMATION_FLIP_FROM_RIGHT);
    }

    public static final void switchToActivityWithResult(android.support.v4.app.Fragment  from, Intent to, int requestCode) {
        sRecentActivity = from.getActivity();
        from.startActivityForResult(to, requestCode);
        doAnimation(from.getActivity(), ANIMATION_FLIP_FROM_RIGHT);
    }

    public static final void switchToActivityAndFinishItself(android.support.v4.app.Fragment  from, Intent to) {
        from.startActivity(to);
        from.getActivity().finish();
        doAnimation(from.getActivity(), ANIMATION_FLIP_FROM_RIGHT);
    }

    public static final void switchBack(android.support.v4.app.Fragment  from) {
        from.getActivity().finish();
        doAnimation(from.getActivity(), ANIMATION_FLIP_FROM_LEFT);
    }

    public static final void switchToActivity(android.support.v4.app.Fragment  from, Intent to, int animation) {
        sRecentActivity = from.getActivity();
        from.startActivity(to);
        doAnimation(from.getActivity(), animation);
    }

    public static final void switchToActivityWithResult(android.support.v4.app.Fragment  from, Intent to, int requestCode, int animation) {
        sRecentActivity = from.getActivity();
        from.startActivityForResult(to, requestCode);
        doAnimation(from.getActivity(), animation);
    }

    public static final void switchToActivityAndFinishItself(android.support.v4.app.Fragment  from, Intent to, int animation) {
        from.startActivity(to);
        from.getActivity().finish();
        doAnimation(from.getActivity(), animation);
    }

    public static final void switchBack(android.support.v4.app.Fragment  from, int animation) {
        from.getActivity().finish();
        doAnimation(from.getActivity(), animation);
    }

    public static final void switchToActivity(Activity from, Intent to) {
        sRecentActivity = from;
        from.startActivity(to);
        doAnimation(from, ANIMATION_FLIP_FROM_RIGHT);
    }

    public static final void switchToActivityWithResult(Activity from, Intent to, int requestCode) {
        sRecentActivity = from;
        from.startActivityForResult(to, requestCode);
        doAnimation(from, ANIMATION_FLIP_FROM_RIGHT);
    }

    public static final void switchToActivityAndFinishItself(Activity from, Intent to) {
        from.startActivity(to);
        from.finish();
        doAnimation(from, ANIMATION_FLIP_FROM_RIGHT);
    }

    public static final void switchBack(Activity from) {
        from.finish();
        doAnimation(from, ANIMATION_FLIP_FROM_LEFT);
    }

    public static final void switchToActivity(Activity from, Intent to, int animation) {
        sRecentActivity = from;
        from.startActivity(to);
        doAnimation(from, animation);
    }

    public static final void switchToActivityWithResult(Activity from, Intent to, int requestCode, int animation) {
        sRecentActivity = from;
        from.startActivityForResult(to, requestCode);
        doAnimation(from, animation);
    }

    public static final void switchToActivityAndFinishItself(Activity from, Intent to, int animation) {
        from.startActivity(to);
        from.finish();
        doAnimation(from, animation);
    }

    public static final void switchBack(Activity from, int animation) {
        from.finish();
        doAnimation(from, animation);
    }

    public static Activity getRecentActivity() {
        return sRecentActivity;
    }

    private static void doAnimation(Activity activity, int animation) {
        switch (animation) {
            case ANIMATION_FADE:
                activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case ANIMATION_FLIP_FROM_LEFT:
                activity.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case ANIMATION_FLIP_FROM_RIGHT:
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case ANIMATION_FLIP_FROM_TOP:
                activity.overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
                break;
            case ANIMATION_FLIP_FROM_BOTTOM:
                activity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);
                break;
        }
    }
}
