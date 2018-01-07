package com.xcoinpay.pizza.pizzawallet.view;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.bean.BaseResponse;
import com.xcoinpay.pizza.pizzawallet.bean.Event;
import com.xcoinpay.pizza.pizzawallet.bean.User;
import com.xcoinpay.pizza.pizzawallet.presenter.RegistPresenter;
import com.xcoinpay.pizza.pizzawallet.util.EncryptUtil;
import com.xcoinpay.pizza.pizzawallet.util.SMSUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class RegistActivity extends BaseActivity<RegistPresenter> {
    @BindView(R.id.regist_user_phone)
    EditText mPhone;
    @BindView(R.id.user_pswd)
    EditText mPwd;
    @BindView(R.id.user_yes_pswd)
    EditText mRPwd;
    @BindView(R.id.regist_tv_sendecode)
    TextView sendeCode;
    @BindView(R.id.regist_et_code)
    EditText mCode;
    @BindView(R.id.user_name)
    EditText user_name;
    @BindView(R.id.regist)
    Button regist;


    @Override
    public RegistPresenter getPresnter() {
        return new RegistPresenter();
    }

    @Override
    public void init() {
        setSupToolbar("注册",0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_regist;
    }

    //点击事件的处理
    @OnClick({R.id.regist_tv_sendecode,R.id.regist})
    public void click(View view){
        switch (view.getId()){
            case R.id.regist_tv_sendecode:
                //发送短信验证码
                sendeCode();

                break;
            case R.id.regist:
                register();
                break;
        }
    }

    private void register() {
        //用户名不能为空
        if(TextUtils.isEmpty(getName())){
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
       //1.判断手机号码和密码是否为空
        if(TextUtils.isEmpty(getPhone())){
            Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(getPwd())){
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(getRPwd())){
            Toast.makeText(this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(getPwd().length()<6){
            Toast.makeText(this, "密码不能少于六位", Toast.LENGTH_SHORT).show();
            return;
        }
        if(getRPwd().length()<6){
            Toast.makeText(this, "确认密码不能少于六位", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!getPwd().equals(getRPwd())){
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(getCode())){
            Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        //判断手机号码的合法性
        boolean isPhone = SMSUtil.judgePhoneNums(this, getPhone());
        if(!isPhone){
            Toast.makeText(this, "手机号码不合法", Toast.LENGTH_SHORT).show();
            return;
        }
        //对密码进行加密加盐
        String salt = "pizzawallet";
        String saltpwd = EncryptUtil.shaEncrypt(getPwd()+salt);
        presenter.regist(getName(),getPhone(),saltpwd,getCode());

    }

    //判断电话号码的合法性
    private void sendeCode() {
        //发送验证码以前判断一下手机号码
       if(TextUtils.isEmpty(getPhone())){
           Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
           return;
       }
        boolean isPhone = SMSUtil.judgePhoneNums(this, getPhone());
       //判断手机号码是否合法
        if(!isPhone){
            Toast.makeText(this, "手机号码不合法", Toast.LENGTH_SHORT).show();
            return;
        }
        //发送短信验证码的方式有两种，0：注册时   1：修改时
        presenter.sendCode(getPhone(),0);
        Toast.makeText(this, "验证码已发送", Toast.LENGTH_SHORT).show();
        initCountDown();


    }

    //倒计时方法
    private void initCountDown() {

//        //CountDownTimer构造器的两个参数分别是第一个参数表示总时间，第二个参数表示间隔时间。
//　　　　//意思就是每隔xxx会回调一次方法onTick，然后xxx之后会回调onFinish方法。
        CountDownTimer timer = new CountDownTimer(32000,1000) {
            int num = 30;
            @Override
            public void onTick(long millisUntilFinished) {
                sendeCode.setText(String.valueOf(num));
                num--;
            }

            @Override
            public void onFinish() {
//　　　　　　　　　　//计时完成调用
                sendeCode.setText("重新发送");
            }
        };
        timer.start();
    }

    public String getPhone(){
        return mPhone.getText().toString().trim();
    }

    public String getPwd(){
        return mPwd.getText().toString().trim();
    }

    public String getCode(){
        return mCode.getText().toString().trim();
    }
    public String getRPwd(){
        return mRPwd.getText().toString().trim();
    }
    public String getName(){
        return user_name.getText().toString().trim();
    }


/**
 * 处理返回来的数据处理
 * 分两种返回码：一个是请求http 返回的code   一个是返回体中的code
 * 这两种都在Event这个类中描述
 *
 * */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event event){
        switch (event.code){
            //发送验证码的判断
            case "204":

                BaseResponse.ResponseResult resultData = (BaseResponse.ResponseResult) event.resultData;
                Toast.makeText(this, resultData.getMsg(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case "504":
               BaseResponse.ResponseResult result = (BaseResponse.ResponseResult) event.data;
                Toast.makeText(this,result.getMsg(), Toast.LENGTH_SHORT).show();
                break;
            case "6":
                Toast.makeText(this, "发送验证码失败", Toast.LENGTH_SHORT).show();
                break;


            //注册的判断
            case "203":
               User registUser = (User) event.data;
                BaseResponse.ResponseResult registresultData = (BaseResponse.ResponseResult) event.resultData;
                Toast.makeText(this,registresultData.getMsg(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegistActivity.this,LoginActivity.class));
                break;
            case "503":
              BaseResponse.ResponseResult registResult = (BaseResponse.ResponseResult) event.data;
                Toast.makeText(this,registResult.getMsg(), Toast.LENGTH_SHORT).show();
                break;
            case "5":
                Toast.makeText(this, "请求连接服务器超时，注册失败", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
