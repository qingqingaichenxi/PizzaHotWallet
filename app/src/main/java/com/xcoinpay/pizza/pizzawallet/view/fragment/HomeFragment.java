package com.xcoinpay.pizza.pizzawallet.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.base.BaseFragment;
import com.xcoinpay.pizza.pizzawallet.bean.Event;
import com.xcoinpay.pizza.pizzawallet.presenter.HomePresenter;
import com.xcoinpay.pizza.pizzawallet.view.AddressBookActivity;
import com.xcoinpay.pizza.pizzawallet.view.TestScanActivity;
import com.xcoinpay.pizza.pizzawallet.view.TradeBookActivity;
import com.xcoinpay.pizza.pizzawallet.view.TransferActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

/**
 * Created by llq on 2017/12/16 0016.
 */

public class HomeFragment extends BaseFragment<HomePresenter> {
    @BindView(R.id.ll_scan)
    LinearLayout ll_scan;
    @BindView(R.id.ll_dressbook)
    LinearLayout ll_dressbook;
    @BindView(R.id.ll_tradebook)
    LinearLayout ll_tradebook;
    @BindView(R.id.btn_barcode)
    Button btn;
    @BindView(R.id.iv)
    ImageView iv;


    @Override
    public HomePresenter getPresnter() {
        return new HomePresenter();
    }

    @Override
    public void init() {
        toolbar_left_iv.setVisibility(View.GONE);
        toolbar_title.setText("首页");
    }

    @Override
    public int getLayoutId() {
        return R.layout.homefragment;
    }

    @OnClick({R.id.ll_scan, R.id.ll_dressbook, R.id.ll_tradebook, R.id.btn_barcode})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.ll_scan:
                startActivity(new Intent(getActivity(), TestScanActivity.class));//跳转到扫描界面
                break;
            case R.id.ll_dressbook:
                startActivity(new Intent(getActivity(),AddressBookActivity.class));
                break;
            case R.id.ll_tradebook:
                startActivity(new Intent(getActivity(),TradeBookActivity.class));
                break;
            case R.id.btn_barcode:
                Bitmap img = encodeAsBitmap("我@俊哥");
                iv.setImageBitmap(img);
                break;
        }
    }
//生成二维码图片的方法
    private Bitmap encodeAsBitmap(String string) {
        return QRCodeEncoder.syncEncodeQRCode(string, BGAQRCodeUtil.dp2px(getActivity(), 150));
    }

    //接收扫完码传递过来的数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void onEvent(String result){
        Toast.makeText(getActivity(),"扫描成功"+result,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), TransferActivity.class);
        intent.putExtra("result",result);
        startActivity(intent);
        sendCoin(result);
        queryCoin(result);
    }

    private void queryCoin(String result) {
//        presnter.queryCoin(result);
    }

    private void sendCoin(String result) {
//        presnter.sendCoin(result);
    }

    //发送完请求返回回来的数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event event){

    }
}
