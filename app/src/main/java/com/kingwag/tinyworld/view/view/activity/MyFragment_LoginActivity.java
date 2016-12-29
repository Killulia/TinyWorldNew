package com.kingwag.tinyworld.view.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.bean.MyUser;
import com.kingwag.tinyworld.view.helper.UserManager;
import com.kingwag.tinyworld.view.utils.ShopLoginSharedpreferencesUtil;

import java.util.List;

/**
 * 我的登录页面
 */
public class MyFragment_LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mEdtPhoneNumber,mEdtPassword;//用户名密码输入框
    private UserManager userManager;//用户管理类
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment__login);


        initView();
    }

    private void initView() {
        userManager = new UserManager(MyFragment_LoginActivity.this);
        findViewById(R.id.iv_back_login).setOnClickListener(this);
        findViewById(R.id.tv_activity_login_register).setOnClickListener(this);
        mEdtPhoneNumber = (EditText) findViewById(R.id.edt_phone_number_login);
        mEdtPassword = (EditText) findViewById(R.id.edt_phone_password_login);
        findViewById(R.id.btn_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_login://返回按钮
                finish();
                break;
            case R.id.tv_activity_login_register://新用户注册按钮
                Intent intent = new Intent();
                intent.setClass(MyFragment_LoginActivity.this, MineFragment_RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
               List<MyUser> users =  userManager.queryAll();
                String phoneNumber = "";
                String password = "";
                for (MyUser userLogin : users) {
                    phoneNumber = userLogin.getMobilePhoneNumber();
                    password = userLogin.getPassword();
                }
                if (phoneNumber.equals(mEdtPhoneNumber.getEditableText().toString()) && password.equals(mEdtPassword.getEditableText().toString())) {
                    ShopLoginSharedpreferencesUtil.putLoginState(mContext,true);
                    Toast.makeText(MyFragment_LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    finish();
                }if (!phoneNumber.equals(mEdtPhoneNumber.getEditableText().toString())){
                ShopLoginSharedpreferencesUtil.putLoginState(mContext,false);
                Toast.makeText(MyFragment_LoginActivity.this, "手机号不正确", Toast.LENGTH_SHORT).show();
            }
                if (!password.equals(mEdtPassword.getEditableText().toString())) {
                    ShopLoginSharedpreferencesUtil.putLoginState(mContext,false);
                    Toast.makeText(MyFragment_LoginActivity.this, "密码不正确", Toast.LENGTH_SHORT).show();

                }

                break;
        }
    }
}
