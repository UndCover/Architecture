package com.undcover.architecture.gate;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.undcover.architecture.common.base.BaseActivity;

public class GateActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate);
    }

    public void onClick(View view) {
//        try {
//            Class clazz = Class.forName("com.undcover.test.TestActivity");
//            Intent intent = new Intent(this, clazz);
//            Switcher.switchToActivity(this, intent);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        navigateTo("/second/next");

        int viewId = view.getId();
        if (viewId == R.id.btn_next) {
            doAnimation(ARouter.getInstance().build("/second/next")
                    .withString("param", "UndCover.Test")
            );
        } else if (viewId == R.id.btn_second_uri) {
            Uri uri = getRouteUri("/second/uri");
            doAnimation(ARouter.getInstance().build(uri)
                    .withString("param1", uri.toString())
            );
        }
    }
}
