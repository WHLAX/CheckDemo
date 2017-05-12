package com.bawei.checkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Show extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);

        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        tv.setText(data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}