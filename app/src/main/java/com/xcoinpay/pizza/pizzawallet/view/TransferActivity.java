package com.xcoinpay.pizza.pizzawallet.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.bean.Balance;
import com.xcoinpay.pizza.pizzawallet.bean.BaseResponse;
import com.xcoinpay.pizza.pizzawallet.bean.Event;
import com.xcoinpay.pizza.pizzawallet.presenter.TransferPresenter;
import com.xcoinpay.pizza.pizzawallet.widget.ProgressDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Base64;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class TransferActivity extends BaseActivity<TransferPresenter> {
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.btn_ok)
    Button btn_ok;
    @BindView(R.id.iv_twocode)
    ImageView iv_twocode;
    @BindView(R.id.count)
    TextView count;

    //    private BookInfo bookInfo;
    private String myAddree;
    private String id;
    private String walletAddress;
    private String coinId;
    private String myBalance;
    private String nonce;
    private ProgressDialog progressDialog;


    @Override
    public TransferPresenter getPresnter() {
        return new TransferPresenter();
    }

    @Override
    public void init() {
        setSupToolbar("传送信息" + coinId, 0);


        //从主页面扫描完得到的结果
//        Intent intent1 = getIntent();
//        String result = intent1.getStringExtra("result");
//        showfragmentData(result);
//        Intent intent = getIntent();
//        bookInfo = (BookInfo) intent.getSerializableExtra("bookinfo");

        String result = getIntent().getStringExtra("result");
        String balance = result.replace("BALANCE:", "");
        Log.i("result++++++++++++++", balance);
        myAddree = balance.split(",")[0];
        id = balance.split(",")[1];
        Log.i("address___________",myAddree);
        Log.i("id__________",id);




    }
//
//    private void showfragmentData(String result) {
//        tv_address.setText(result);
//        tv_name.setText(result);
//        iv_twocode.setImageBitmap(QRCodeEncoder.syncEncodeQRCode(result, BGAQRCodeUtil.dp2px(this, 150)));
//    }

    @Override
    protected void onStart() {
        super.onStart();
        progressDialog = new ProgressDialog(this,"加载数据中 ... ...");
        progressDialog.show();
        presenter.queryCoin(myAddree,id);


    }

    private void showData() {
        tv_address.setText(walletAddress);
        tv_name.setText(coinId);
        count.setText(myBalance);
        iv_twocode.setImageBitmap(QRCodeEncoder.syncEncodeQRCode(getString(), BGAQRCodeUtil.dp2px(this, 150)));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_transfer;
    }

    @OnClick(R.id.btn_ok)
    public void click(View view) {

        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event event) {
        progressDialog.dismiss();
        switch (event.code) {
            case Event.Code.QuerySuccessCode:
                Balance balance = (Balance) event.data;
                walletAddress = balance.getWalletAddress();
                coinId = balance.getSymbol();
                myBalance = balance.getBalance();
                nonce = balance.getNonce();

                BaseResponse.ResponseResult result = (BaseResponse.ResponseResult) event.resultData;
                Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
                showData();
                break;
            case Event.Code.QueryFailCode:

                BaseResponse.ResponseResult result1 = (BaseResponse.ResponseResult) event.resultData;
                Toast.makeText(this, result1.getMsg(), Toast.LENGTH_SHORT).show();
                break;
            case Event.Code.RequestQueryFailCode:
                Toast.makeText(this, "请求服务器失败", Toast.LENGTH_SHORT).show();
                break;

        }

    }
    public String getString(){
        //吧数据进行编码才能用二维码出数据，

        String string = android.util.Base64.encodeToString((coinId+","+nonce+","+myBalance+","+walletAddress).getBytes(),0);
        return string;
    }



}
