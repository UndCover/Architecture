package com.undcover.architecture.second;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/second/uri")
public class UriSecondActivity extends AppCompatActivity {
    String param;

    TextView tvSecondUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uri_second);

        param = getIntent().getStringExtra("param1");

        tvSecondUri = findViewById(R.id.tv_second_uri);

        setChildrenData();
    }

    private void setChildrenData() {
        tvSecondUri.setText("Uri : "+param);
    }
}
