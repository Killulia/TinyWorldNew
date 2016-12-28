package com.kingwag.tinyworld.view.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kingwag.tinyworld.R;


public class MineFragment_RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mEdtPhoneNumber, mEdtPhoneYanzhengma,mEdtPassword;//手机号验证码密码输入框
    private TextView mTvYanzhengma;//获取验证码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_fragment__register);

        initView();

    }

    private void initView() {
        findViewById(R.id.tv_agree_register).setOnClickListener(this);//协议
        mEdtPhoneNumber = (EditText) findViewById(R.id.edt_phone_number);
        mEdtPhoneYanzhengma = (EditText) findViewById(R.id.edt_phone_yanzhengma);
        mEdtPassword = (EditText) findViewById(R.id.edt_password);
        findViewById(R.id.tv_yanzhengma).setOnClickListener(this);//获取验证码
        findViewById(R.id.btn_register).setOnClickListener(this);//注册按钮
        findViewById(R.id.iv_back_register).setOnClickListener(this);//返回按钮
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_agree_register:
                Intent intent = new Intent();
                intent.setClass(MineFragment_RegisterActivity.this, MineFragment_RegisterAgreement.class);
                startActivity(intent);
                break;
            case R.id.tv_yanzhengma:// 获取验证码
                if ("".equals(mEdtPhoneNumber.getText().toString())) {
                    Toast.makeText(MineFragment_RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else {

                }

                break;
            case R.id.btn_register://注册按钮
                if ("".equals(mEdtPhoneNumber.getText().toString())) {
                    Toast.makeText(MineFragment_RegisterActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(MineFragment_RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                }

                finish();
                break;
            case R.id.iv_back_register://返回按钮
                finish();
                break;
        }


    }
}
