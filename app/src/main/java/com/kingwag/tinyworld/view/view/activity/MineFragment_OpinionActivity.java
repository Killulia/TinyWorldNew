package com.kingwag.tinyworld.view.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.kingwag.tinyworld.R;
import com.kingwag.tinyworld.view.utils.ShopLoginSharedpreferencesUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                if (!mEdtPhoneNumber.getEditableText().toString().equals("")||!mEdtSuggestion.getEditableText().toString().equals("")){
                    //判断手机号
                    String regExp="^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
                    Pattern p= Pattern.compile(regExp);
                    Matcher m =p.matcher(mEdtPhoneNumber.getEditableText().toString());
                    if (m.find()){
                        Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
                        mEdtSuggestion.setText("");
                        mEdtPhoneNumber.setText("");
                    }else {
                        Toast.makeText(this, "请输入有效手机号", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(this, "建议和手机号不能为空", Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }


}
