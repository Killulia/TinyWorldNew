package com.kingwag.tinyworld.view.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.kingwag.tinyworld.R;

public class MineFragment_OpinionActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mEdtSuggestion,mEdtPhoneNumber;//定义全局输入框对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_fragment__opinion);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_login).setOnClickListener(this);//返回按钮
     mEdtSuggestion= (EditText) findViewById(R.id.edt_suggestion);//实例化意见框
       mEdtPhoneNumber = (EditText) findViewById(R.id.edt_opinion_activity_phone);//实例化手机号输入
        findViewById(R.id.tv_suggestion_commit).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_login://返回按钮
                finish();
                break;
            case R.id.tv_suggestion_commit:
                Uri uri = Uri.parse("smsto:18624360288");
                Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
                intent.putExtra("Suggestion", mEdtSuggestion.getText().toString() + mEdtPhoneNumber.getText().toString());
                startActivity(intent);
                break;
        }
    }
}
