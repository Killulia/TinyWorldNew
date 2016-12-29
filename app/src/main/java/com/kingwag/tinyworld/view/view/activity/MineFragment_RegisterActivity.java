package com.kingwag.tinyworld.view.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.bean.MyUser;
import com.kingwag.tinyworld.view.helper.UserManager;

import java.util.ArrayList;
import java.util.List;


public class MineFragment_RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mEdtPhoneNumber,mEdtPassword;//手机号验证码密码输入框
    private UserManager userManager;//用户管理类
    private int count;//计数器
    private ImageView mIvPwdVisible;//密码是否显示
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_fragment__register);

        initView();

    }

    private void initView() {
        userManager = new UserManager(MineFragment_RegisterActivity.this);//创建UserManager实例
        findViewById(R.id.tv_agree_register).setOnClickListener(this);//协议
        mEdtPhoneNumber = (EditText) findViewById(R.id.edt_phone_number);
        mEdtPassword = (EditText) findViewById(R.id.edt_password);
        mIvPwdVisible = (ImageView) findViewById(R.id.iv_pwd_visible);//设置密码是否可见
        mIvPwdVisible.setOnClickListener(this);
        findViewById(R.id.btn_register).setOnClickListener(this);//注册按钮
        findViewById(R.id.iv_back_register).setOnClickListener(this);//返回按钮

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_register://返回按钮
                finish();
                break;
            case R.id.btn_register://注册按钮
                List<MyUser> users = userManager.queryAll();
                Log.e("MyLog", "---------users.size" + users.size());
                String phoneNumberItem ="";
                for (MyUser userItem : users) {
                    phoneNumberItem= userItem.getMobilePhoneNumber();
                }
                if ("".equals(mEdtPhoneNumber.getEditableText().toString())) {
                    Toast.makeText(MineFragment_RegisterActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                }
               else if ("".equals(mEdtPassword.getEditableText().toString())) {
                    Toast.makeText(MineFragment_RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                } else if (phoneNumberItem.equals(mEdtPhoneNumber.getEditableText().toString())) {
                    Toast.makeText(MineFragment_RegisterActivity.this,"该手机号已经注册过",Toast.LENGTH_SHORT).show();
                } else {

                    String phoneNumber = mEdtPhoneNumber.getEditableText().toString();
                    String password = mEdtPassword.getEditableText().toString();
                    MyUser user = new MyUser();
                    user.setMobilePhoneNumber(phoneNumber);
                    user.setPassword(password);
                    try {
                        userManager.insert(user);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MineFragment_RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }

                break;

            case R.id.iv_pwd_visible://设置密码可见
                count++;
                if (count % 2 == 0) {
                    mIvPwdVisible.setImageResource(R.mipmap.pwd_hide);
                    mEdtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT);

                }
                if (count % 2 == 1) {
                    mIvPwdVisible.setImageResource(R.mipmap.pwd_show);
                    //设置密码可见
                    mEdtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                }
                break;
        }


    }
}
