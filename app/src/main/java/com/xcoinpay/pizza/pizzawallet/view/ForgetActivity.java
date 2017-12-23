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
import com.xcoinpay.pizza.pizzawallet.presenter.ForgetPresenter;
import com.xcoinpay.pizza.pizzawallet.util.EncryptUtil;
import com.xcoinpay.pizza.pizzawallet.util.SMSUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.http.PUT;

public class ForgetActivity extends BaseActivity<ForgetPresenter> {

    @BindView(R.id.forget_commit)
    Button coomit;
    @BindView(R.id.forget_user_phone)
    EditText user_phone;
    @BindView(R.id.tv_getcode)
    TextView tv_getcode;
    @BindView(R.id.user_code)
    EditText user_code;

    @BindView(R.id.new_pwd)
    EditText new_pwd;
    @BindView(R.id.reset_pwd)
    EditText reset_pwd;

    @Override
    public ForgetPresenter getPresnter() {
        return new ForgetPresenter();
    }

    @Override
    public void init() {
        setSupToolbar("忘记密码",0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String string){

    }

    @OnClick({R.id.tv_getcode,R.id.forget_commit})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_getcode:
                sendCode();

                break;
            case R.id.forget_commit:
                //判断手机号码和验证码是否为空
                if(TextUtils.isEmpty(getPhone())){
                    Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(getCode())){
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断手机号码是否是合法的
                if(!SMSUtil.judgePhoneNums(this,getPhone())){
                    Toast.makeText(this, "手机号码不合法", Toast.LENGTH_SHORT).show();
                    return;
                }

                //判断密码是否为空
                if(TextUtils.isEmpty(getNewPwd())){
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断确认密码是否为空
                if(TextUtils.isEmpty(getResetPed())){
                    Toast.makeText(this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断两次的密码不能少于六位数
                if(getNewPwd().length()<6){
                    Toast.makeText(this, "密码不能少于六位数", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(getResetPed().length()<6){
                    Toast.makeText(this, "确认密码不能少于六位数", Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断两次密码是否一致
                if(!getNewPwd().equals(getResetPed())){
                    Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                //把密码进行加密
                String salt = "pizzawallet";
                String saltPwd = EncryptUtil.shaEncrypt(getNewPwd() + salt);
                //调用后台的提交密码的借口（把密码提交给后台）

                presenter.commitCode(saltPwd,getPhone(),getCode());
                Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(this,NewPwdActivity.class));
                break;
        }
    }

    private void sendCode() {
        //判断手机号码是否为空
        if(TextUtils.isEmpty(getPhone())){
            Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        //判断手机号码是否是合法的
        if(!SMSUtil.judgePhoneNums(this,getPhone())){
            Toast.makeText(this, "手机号码不合法", Toast.LENGTH_SHORT).show();
            return;
        }

        //把手机号和验证码发送给后台
        presenter.sendCode(getPhone(),1);
        Toast.makeText(this, "验证码发送成功", Toast.LENGTH_SHORT).show();
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
                tv_getcode.setText(String.valueOf(num));
                num--;
            }

            @Override
            public void onFinish() {
//　　　　　　　　　　//计时完成调用
                tv_getcode.setText("重新发送");
            }
        };
        timer.start();
    }

    public String getPhone(){
        return user_phone.getText().toString().trim();
    }
    public String getCode(){
        return user_code.getText().toString().trim();
    }


    public String getNewPwd(){
        return new_pwd.getText().toString().trim();
    }
    public String getResetPed(){
        return reset_pwd.getText().toString().trim();
    }
}
