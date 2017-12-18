package com.xcoinpay.pizza.pizzawallet.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.view.fragment.HomeFragment;
import com.xcoinpay.pizza.pizzawallet.view.fragment.MyFragment;
import com.xcoinpay.pizza.pizzawallet.widget.TabButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fl_main)
    LinearLayout main_container;
   @BindView(R.id.tab_home)
   TabButton mTabHome;
   @BindView(R.id.tab_my)
   TabButton mTabMy;
    public ArrayList<Fragment> fgs;
    private List<View> mTabButtonList;
//    private int[] mTitles = {R.string.follow_home, R.string.my};
    private int mCurrIndex = -1;

    public static int REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        initEvent();
        change(0);
    }



    private void initEvent() {
        for (int i = 0; i < mTabButtonList.size(); i++) {
//            mTabButtonList.get(i).setOnClickListener(this);
            mTabButtonList.get(i).setTag(i);
        }
    }

    private void initFragment() {
        fgs = new ArrayList<>();
        fgs.add(new HomeFragment());
        fgs.add(new MyFragment());

        mTabButtonList = new ArrayList<>();
        mTabButtonList.add(mTabHome);
        mTabButtonList.add(mTabMy);

    }

    @OnClick({R.id.tab_home,R.id.tab_my})
    public void click(View view){
        int number = (Integer) view.getTag();
        view.setFocusable(true);
        change(number);
    }

//    @Override
//    public void onClick(View v) {
//        int number = (Integer) v.getTag();
//        change(number);
//    }

    public void change(int index) {
//        if (index == 1 || index == 2) {
//            if (Constant.loginStatus == false) {
//                // mTabButtonList.get(mCurrIndex).setFocusable(true);
//                Intent intent = new Intent(this, LoginActivity.class);
//                startActivity(intent);
//                return;
//            }
//        }
        //1f是选中的。0f是没有选中的
        mTabHome.setAlpha(index==0? 1f : 0f);
        mTabMy.setAlpha(index==1? 1f : 0f);
        if (index != mCurrIndex) {
            mCurrIndex = index;
            changeFragment(index);
//            toolbar.setText(mTitles[index]);
//            mTitle.setVisibility(View.VISIBLE);
//            mToolbar.findViewById(R.id.toolbar_right).setVisibility(View.GONE);
        }

    }

    private void changeFragment(int index) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.fl_main, fgs.get(index));
        ft.commit();

    }


//
//@Override
//protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//    IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//    if(result != null) {
//        if(result.getContents() == null) {
//            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(this, "Scanned: 扫描成功" + result.getContents(), Toast.LENGTH_LONG).show();
//        }
//    } else {
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//}
}
