package com.xcoinpay.pizza.pizzawallet.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.bean.Event;
import com.xcoinpay.pizza.pizzawallet.presenter.NewPwdPresenter;
import com.xcoinpay.pizza.pizzawallet.util.EncryptUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class NewPwdActivity extends BaseActivity<NewPwdPresenter> {
    @BindView(R.id.new_pwd)
    EditText new_pwd;
    @BindView(R.id.reset_pwd)
    EditText reset_pwd;
    @BindView(R.id.commit)
    Button btn_commit;

    @Override
    public NewPwdPresenter getPresnter() {
        return new NewPwdPresenter();
    }

    @Override
    public void init() {
        setSupToolbar("重置密码",0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_pwd;
    }

    @OnClick({R.id.commit})
    public void click(View view){
        switch (view.getId()){
            case R.id.commit:
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
                presenter.commit(saltPwd);
                Toast.makeText(this, "重置密码成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event event){

    }

    public String getNewPwd(){
        return new_pwd.getText().toString().trim();
    }
    public String getResetPed(){
        return reset_pwd.getText().toString().trim();
    }
}
