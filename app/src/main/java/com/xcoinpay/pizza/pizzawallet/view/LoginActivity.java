

package com.xcoinpay.pizza.pizzawallet.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.bean.Event;
import com.xcoinpay.pizza.pizzawallet.contant.Contant;
import com.xcoinpay.pizza.pizzawallet.presenter.LoginPresenter;
import com.xcoinpay.pizza.pizzawallet.util.EncryptUtil;
import com.xcoinpay.pizza.pizzawallet.util.SMSUtil;
import com.xcoinpay.pizza.pizzawallet.util.SPUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> {

    @BindView(R.id.login_tv_regist)
    TextView tv_regist;
    @BindView(R.id.login_tv_forgetpwd)
    TextView tv_forgetpwd;
    @BindView(R.id.loagin)
    Button login;
    @BindView(R.id.user_phone)
    EditText user_phone;
    @BindView(R.id.user_pswd)
    EditText user_pswd;
    @BindView(R.id.iv_clearphone)
    ImageView clearphone;
    @BindView(R.id.iv_clearpwd)
    ImageView clearpwd;




    @Override
    public LoginPresenter getPresnter() {
        return new LoginPresenter();
    }

    @Override
    public void init() {
        setSupToolbar("登录",0);


    }

    @Override
    public int getLayoutId() {
        return R.layout.login_activity;
    }


//接受传递过来的数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void onEvent(Event event){
        switch (event.code){
            case 1:
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                //把返回的用户信息和token保存到本地
                break;
            case 2:
                Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    @OnClick({R.id.login_tv_regist,R.id.login_tv_forgetpwd,R.id.loagin,R.id.iv_clearphone,R.id.iv_clearpwd})
    public void click(View view){
        switch (view.getId()){
            case R.id.login_tv_regist:
                startActivity(new Intent(this,RegistActivity.class));
                break;
            case R.id.login_tv_forgetpwd:
                startActivity(new Intent(this,ForgetActivity.class));
                break;
            case R.id.loagin:
                login();
                break;
            case R.id.iv_clearphone:
                user_phone.setText("");
                break;
            case R.id.iv_clearpwd:
                user_pswd.setText("");
                break;
        }
    }


    private void login() {

        //1.校验手机号码和密码是否为空
        if(TextUtils.isEmpty(getPhone())){
            Toast.makeText(this,"手机号码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(getPwd())){
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(getPwd().length()<6){
            Toast.makeText(this,"密码不能少于六位",Toast.LENGTH_SHORT).show();
            return;
        }
        //2.校验手机号码是否合法
        boolean isPhoneOk = SMSUtil.judgePhoneNums(this, getPhone());

        if(!isPhoneOk ){
            Toast.makeText(this, "手机号码不合法", Toast.LENGTH_SHORT).show();
            return;
        }
        //3.对密码进行加密
        String salt = "pizzawallet";
        String saltpwd = EncryptUtil.shaEncrypt(getPwd() + salt);

        presenter.login(getPhone(),saltpwd);

    }

    public String getPhone(){

        return user_phone.getText().toString().trim();
    }
    public String getPwd(){

        return  user_pswd.getText().toString().trim();
    }
}
