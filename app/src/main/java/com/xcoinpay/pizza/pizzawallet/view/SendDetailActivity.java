package com.xcoinpay.pizza.pizzawallet.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.bean.Event;
import com.xcoinpay.pizza.pizzawallet.presenter.SendDetailPresenter;
import com.xcoinpay.pizza.pizzawallet.util.SPUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendDetailActivity extends BaseActivity<SendDetailPresenter> {


    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_coinid)
    TextView tvCoinid;
    @BindView(R.id.tv_coin_address)
    TextView tvCoinAddress;
    @BindView(R.id.btn_send)
    Button btnSend;

    private static final String USER_ID = null;
    private String result;

    @Override
    public SendDetailPresenter getPresnter() {
        return new SendDetailPresenter();
    }

    @Override
    public void init() {
        setSupToolbar("发送交易详情", 0);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_send_detail;
    }

    //扫描发送数据传过来的信息
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(Event event ) {
        switch (event.code){
            case Event.Code.SendCode:
                result = (String) event.data;
                Toast.makeText(this, "扫描成功" + result, Toast.LENGTH_SHORT).show();

                break;
//            case :
//                break;
//            case :
//                break;

        }


    }

    private void sendCoin(String result) {
        String userId = SPUtils.getString(this, USER_ID);
//        presenter.sendCoin(result,"4028");
    }



    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        Intent intent = new Intent(this, TradeBookActivity.class);
        intent.putExtra("result", "0000");
        startActivity(intent);

        sendCoin(result);
    }


}
