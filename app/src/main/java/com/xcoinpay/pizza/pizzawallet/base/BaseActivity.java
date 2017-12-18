package com.xcoinpay.pizza.pizzawallet.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcoinpay.pizza.pizzawallet.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Created by llq on 2017/12/15.
 */

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView<P> ,View.OnClickListener{

    public P presenter;
    private View baseview;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseview = View.inflate(this,R.layout.base_activity, null);
        FrameLayout contener = baseview.findViewById(R.id.baseview_fl_contaner);
        setContentView(baseview);
        View childView = View.inflate(this,getLayoutId(), null);
        contener.addView(childView);

        ButterKnife.bind(this,childView);

        initToolbar();
        init();
        presenter = getPresnter();
        if(presenter!=null){
            presenter.init();
        }


        EventBus.getDefault().register(this);
    }

     TextView toolbar_title;
     ImageView toolbar_right_iv;
     ImageView toolbar_left_iv;
    protected  void initToolbar(){
        toolbar_left_iv = baseview.findViewById(R.id.toolbar_left_iv);
        toolbar_right_iv = baseview.findViewById(R.id.toolbar_right_iv);
        toolbar_title = baseview.findViewById(R.id.toolbar_tv);

        toolbar_left_iv.setOnClickListener(this);
        toolbar_right_iv.setOnClickListener(this);

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null)
            presenter.onDetory();
        }
    public void setSupToolbar(String title,int imgId){
        toolbar_title.setText(title);

        if(imgId>0){
            toolbar_right_iv.setVisibility(View.VISIBLE);
            toolbar_right_iv.setBackgroundResource(imgId);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_left_iv:
                finish();
                break;
            case R.id.toolbar_right_iv:
                onRightClick();
                break;
        }

    }

    protected  void onRightClick(){};
}
