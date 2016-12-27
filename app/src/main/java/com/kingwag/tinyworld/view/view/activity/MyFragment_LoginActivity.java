package com.kingwag.tinyworld.view.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kingwag.tinyworld.R;

public class MyFragment_LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment__login);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_login://返回按钮
                finish();
                break;
        }
    }
}
