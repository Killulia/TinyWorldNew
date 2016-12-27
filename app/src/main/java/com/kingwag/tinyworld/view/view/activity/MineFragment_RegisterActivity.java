package com.kingwag.tinyworld.view.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.bean.MyUser;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

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
        MyUser user = new MyUser();
        user.setMobilePhoneNumber(mEdtPhoneNumber.getText().toString());
        user.setPassword(mEdtPassword.getText().toString());
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
                    BmobSMS.requestSMSCode(mEdtPhoneNumber.getText().toString(),"模板名称", new QueryListener<Integer>() {

                        @Override
                        public void done(Integer smsId,BmobException ex) {
                            if(ex==null){//验证码发送成功
                                Log.i("smile", "短信id："+smsId);//用于查询本次短信发送详情
                            }
                        }
                    });
                }

                break;
            case R.id.btn_register://注册按钮
                if ("".equals(mEdtPhoneNumber.getText().toString())) {
                    Toast.makeText(MineFragment_RegisterActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                } else {
                    BmobUser.signOrLoginByMobilePhone(mEdtPhoneNumber.getText().toString(), mEdtPhoneYanzhengma.getText().toString(), new LogInListener<MyUser>() {

                        @Override
                        public void done(MyUser user, BmobException e) {
                            if(user!=null){
                                Log.i("smile","用户登陆成功");
                            }
                        }
                    });
                    Toast.makeText(MineFragment_RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                }


//                user.signOrLogin(mEdtPhoneYanzhengma.getText().toString(), new SaveListener<MyUser>() {
//
//
//                    @Override
//                    public void done(MyUser user, BmobException e) {
//                        if(e==null){
//                            Toast.makeText(MineFragment_RegisterActivity.this,"注册或登录成功",Toast.LENGTH_SHORT).show();
//                            Log.e("MyLog", ""+user.getUsername()+"-"+user.getObjectId());
//                        }else{
//                            Toast.makeText(MineFragment_RegisterActivity.this,"注册或登录成功",Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//
//                });
//                Toast.makeText(MineFragment_RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.iv_back_register://返回按钮
                finish();
                break;
        }


    }
}
