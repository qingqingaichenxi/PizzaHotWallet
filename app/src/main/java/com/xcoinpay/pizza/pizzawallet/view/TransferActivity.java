package com.xcoinpay.pizza.pizzawallet.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.base.NothingPresenter;
import com.xcoinpay.pizza.pizzawallet.bean.BookInfo;
import com.xcoinpay.pizza.pizzawallet.bean.Event;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class TransferActivity extends BaseActivity<NothingPresenter> {
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.btn_ok)
    Button btn_ok;
    @BindView(R.id.iv_twocode)
    ImageView iv_twocode;

    private BookInfo bookInfo;


    @Override
    public NothingPresenter getPresnter() {
        return new NothingPresenter();
    }

    @Override
    public void init() {
        Intent intent = getIntent();
        bookInfo = (BookInfo) intent.getSerializableExtra("bookinfo");

        setSupToolbar("传送信息"+bookInfo.getCoin(), 0);
        showData();

    }

    private void showData() {
        tv_address.setText(bookInfo.getAddress());
        tv_name.setText(bookInfo.getName());
        iv_twocode.setImageBitmap(QRCodeEncoder.syncEncodeQRCode(bookInfo.getAddress(), BGAQRCodeUtil.dp2px(this, 150)));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_transfer;
    }

    @OnClick(R.id.btn_ok)
    public void click(View view){
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event event){


    }
}
