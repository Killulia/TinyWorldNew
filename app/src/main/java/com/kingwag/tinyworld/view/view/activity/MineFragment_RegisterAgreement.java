package com.kingwag.tinyworld.view.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kingwag.tinyworld.R;

public class MineFragment_RegisterAgreement extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_fragment__register_agreement);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_agreement_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_agreement_back:
                finish();
                break;
        }
    }
}
