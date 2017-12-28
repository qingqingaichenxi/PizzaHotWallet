package com.xcoinpay.pizza.pizzawallet.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.base.NothingPresenter;
import com.xcoinpay.pizza.pizzawallet.bean.Event;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class CommitTokenActivity extends BaseActivity<NothingPresenter> {
    @BindView(R.id.et_address)
    EditText et_address;
    @BindView(R.id.btn_ok)
    Button btn_ok;
    @BindView(R.id.iv_twocode)
    ImageView iv_twocode;

    @Override
    public NothingPresenter getPresnter() {
        return new NothingPresenter();
    }

    @Override
    public void init() {
        setSupToolbar("提交Token资产",0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_commit_token;
    }
    @OnClick({R.id.btn_ok,R.id.iv_twocode})
    public void click(View view){
        switch (view.getId()){
            case R.id.btn_ok:
                if(TextUtils.isEmpty(et_address.getText().toString())){
                    Toast.makeText(this, "请输入合约地址", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            case R.id.iv_twocode:
                startActivity(new Intent(this, TestScanActivity.class));//跳转到扫描界面
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event event){

    }
}
