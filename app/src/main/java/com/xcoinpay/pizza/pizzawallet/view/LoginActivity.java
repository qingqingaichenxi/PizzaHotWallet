

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
import com.xcoinpay.pizza.pizzawallet.contant.Contant;
import com.xcoinpay.pizza.pizzawallet.presenter.LoginPresenter;
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
    public  void onEvent(String string){

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
        SMSUtil.judgePhoneNums(this,getPhone());
        boolean mobileNO = SMSUtil.isMobileNO(getPhone());
        boolean pwdNo = TextUtils.isEmpty(getPwd());
        if(mobileNO && !pwdNo){
            presenter.login(mobileNO,pwdNo);
        }

        //保存用戶的信息
        SPUtils.putString(this, Contant.username,getPhone());
        SPUtils.putString(this,Contant.userpwd,getPwd());


    }

    public String getPhone(){

        return user_phone.getText().toString().trim();
    }
    public String getPwd(){

        return  user_pswd.getText().toString().trim();
    }
}
