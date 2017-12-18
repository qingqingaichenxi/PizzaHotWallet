package com.xcoinpay.pizza.pizzawallet.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.xcoinpay.pizza.pizzawallet.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Created by llq on 2017/12/15.
 */

public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IView<P> {


    public P presnter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View basefragmentview = View.inflate(getContext(),R.layout.basefragment, null);
        FrameLayout fl_concainer = basefragmentview.findViewById(R.id.fl_fragment);

        View childview  = View.inflate(getContext(), getLayoutId(), null);
        ButterKnife.bind(this,childview);
        fl_concainer.addView(childview);

        initToolbar(basefragmentview);
        //每一个view实现这个方法。初始化view的
        init();

        presnter = getPresnter();
        if(presnter !=null){
            presnter.init();
        }


        EventBus.getDefault().register(this);
        return basefragmentview;

    }

    public ImageView toolbar_left_iv;
    public ImageView toolbar_right_iv;
    public TextView toolbar_title;
    public LinearLayout toolbar;
    protected  void initToolbar(View basefragmentview){
        toolbar = basefragmentview.findViewById(R.id.toolbar);
        toolbar_left_iv = basefragmentview.findViewById(R.id.toolbar_left_iv);
        toolbar_right_iv = basefragmentview.findViewById(R.id.toolbar_right_iv);
        toolbar_title = basefragmentview.findViewById(R.id.toolbar_tv);
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        //presenter释放资源的
        if(presnter!=null){
            presnter.onDetory();
        }
    }
}
