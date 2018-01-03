

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
import com.xcoinpay.pizza.pizzawallet.bean.BaseResponse;
import com.xcoinpay.pizza.pizzawallet.bean.Event;
import com.xcoinpay.pizza.pizzawallet.bean.User;
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
//    @BindView(R.id.user_name)
//    EditText user_name;

    @BindView(R.id.iv_clearpwd)
    ImageView clearpwd;

    private static final  String USER_NAEM = null;
    private static final String USER_TEL = null;
    private static final String USER_ID = null;
    private static final String USER_PASSWD = null;
    private static final String USER_CTIME = null;



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
            case "200":
                //保存数据SP中
                User user = (User) event.data;
                BaseResponse.ResponseResult  resultData = (BaseResponse.ResponseResult) event.resultData;

                SPUtils.putString(this, USER_NAEM,user.getName());
                SPUtils.putString(this, USER_TEL,user.getTel());
                SPUtils.putString(this,USER_CTIME,user.getCtime());
                SPUtils.putString(this,USER_ID,user.getId());
                SPUtils.putString(this,USER_PASSWD,user.getPassword());

                startActivity(new Intent(LoginActivity.this,MainActivity.class));

                Toast.makeText(this, resultData.getMsg(), Toast.LENGTH_SHORT).show();
                //把返回的用户信息和token保存到本地
                break;
            case "2":

                Toast.makeText(this, "连接服务器失败登录失败", Toast.LENGTH_SHORT).show();
                break;
            case "500":

                BaseResponse.ResponseResult result = (BaseResponse.ResponseResult) event.data;
                Toast.makeText(this,result.msg, Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @OnClick({R.id.login_tv_regist,R.id.login_tv_forgetpwd,R.id.loagin,R.id.iv_clearpwd})
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
//        if(TextUtils.isEmpty(getName())){
//            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
//            return;
//        }
        //2.校验手机号码是否合法
        boolean isPhoneOk = SMSUtil.judgePhoneNums(this, getPhone());

        if(!isPhoneOk ){
            Toast.makeText(this, "手机号码不合法", Toast.LENGTH_SHORT).show();
            return;
        }
        //3.对密码进行加密
        String salt = "pizzawallet";
        String saltpwd = EncryptUtil.shaEncrypt(getPwd() + salt);
        String phone = getPhone();
        presenter.login(phone,saltpwd);

    }

    public String getPhone(){

        return user_phone.getText().toString().trim();
    }
    public String getPwd(){

        return  user_pswd.getText().toString().trim();
    }

}
