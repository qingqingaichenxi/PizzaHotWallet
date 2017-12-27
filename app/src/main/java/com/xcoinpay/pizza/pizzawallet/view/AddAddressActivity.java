package com.xcoinpay.pizza.pizzawallet.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.presenter.AddAddressPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class AddAddressActivity extends BaseActivity<AddAddressPresenter> {
    @BindView(R.id.ll_selectbi)
    LinearLayout ll_selectebi;
    @BindView(R.id.selecte_tv_bi)
    EditText selete_bi;
    @BindView(R.id.add_iv_bi)
    ImageView add_iv_bi;
    @BindView(R.id.btn_add)
    Button btn_add;
    @BindView(R.id.addaddress_et_remark)
    EditText addaddress_et_remark;
    @BindView(R.id.writeaddress)
    EditText writeaddress;


    @Override
    public AddAddressPresenter getPresnter() {
        return new AddAddressPresenter();
    }

    @Override
    public void init() {
        setSupToolbar("添加地址",0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_address;
    }

   @OnClick({R.id.ll_selectbi,R.id.add_iv_bi,R.id.btn_add})
    public void click(View view ){
        switch (view.getId()){
            case R.id.ll_selectbi:
                startActivity(new Intent(this,SelectBiActivity.class));
                break;
            case R.id.add_iv_bi:
                startActivity(new Intent(this, TestScanActivity.class));//跳转到扫描界面
                break;
            case R.id.btn_add:
                addCoin();
                break;

        }
   }

    private void addCoin() {
        //判断币种是否是空的
        if(TextUtils.isEmpty(selete_bi.getText().toString())){
            Toast.makeText(this, "请选择币种", Toast.LENGTH_SHORT).show();
            return;
        }
        //判断备注是否是空的
        if(TextUtils.isEmpty(addaddress_et_remark.getText().toString())){
            Toast.makeText(this, "请填写备注", Toast.LENGTH_SHORT).show();
            return;
        }
        //判断地址是否是空的
        if(TextUtils.isEmpty(writeaddress.getText().toString())){
            Toast.makeText(this, "请填写或者扫描地址", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void onEvent(String result){
        String cutAddress = cutAddress(result);
        writeaddress.setText(cutAddress);
    }

    private String  cutAddress(String result) {
        //字符串中是否含只有：这个字符    等于-1表示这个字符串中没有这个字符
        if(result.indexOf(":")!=-1){
            int indexStart = result.indexOf(":");
            String address = result.substring(indexStart+1,result.length()-1);
            return address;
        }

        if(result.indexOf(":")!=-1 && result.indexOf("?")!=-1){
            int satrtIndex = result.indexOf(":");
            int endIndex = result.indexOf("?");
            String address = result.substring(satrtIndex+1, endIndex-1);
            return address;
        }
        if(result.indexOf(":")==-1 && result.indexOf("?")==-1){
            return  result;
        }

        return  null;

    }


}
