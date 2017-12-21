package com.xcoinpay.pizza.pizzawallet.view;

import android.content.Intent;
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
                presenter.sendCode(getPhone());
                Toast.makeText(this, "验证码发送成功", Toast.LENGTH_SHORT).show();
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
                presenter.commitCode(getPhone(),getCode());
                Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,NewPwdActivity.class));
                break;
        }
    }

    public String getPhone(){
        return user_phone.getText().toString().trim();
    }
    public String getCode(){
        return user_code.getText().toString().trim();
    }
}
