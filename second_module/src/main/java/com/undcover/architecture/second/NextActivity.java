package com.undcover.architecture.second;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.undcover.architecture.common.base.BaseActivity;
import com.undcover.architecture.common.utils.SmartLog;
import com.undcover.architecture.common.utils.SmartToast;


@Route(path = "/second/next")
public class NextActivity extends BaseActivity {
    private String clickText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        clickText = getIntent().getStringExtra("param");
    }

    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_second_click) {
            if (clickText != null) {
                SmartToast.showCancelableToast(clickText);
            } else {
                SmartToast.showCancelableToast("click me!");
            }
        } else if (i == R.id.btn_second_uri) {
            Uri uri = getRouteUri("/second/uri");
            doAnimation(ARouter.getInstance().build(uri)
                    .withString("param1", TAG)
            );
        }
    }
}
