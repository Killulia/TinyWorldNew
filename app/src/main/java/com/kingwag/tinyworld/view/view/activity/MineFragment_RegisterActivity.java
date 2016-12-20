package com.kingwag.tinyworld.view.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.kingwag.tinyworld.R;

public class MineFragment_RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_fragment__register);

        initView();

    }

    private void initView() {
       findViewById(R.id.tv_agree_register).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_agree_register:
                intent.setClass(MineFragment_RegisterActivity.this, MineFragment_RegisterAgreement.class);
                break;
        }
        startActivity(intent);
    }
}
