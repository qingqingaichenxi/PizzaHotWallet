package com.xcoinpay.pizza.pizzawallet.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.adapter.AddBookAdapter;
import com.xcoinpay.pizza.pizzawallet.base.BaseActivity;
import com.xcoinpay.pizza.pizzawallet.bean.BookInfo;
import com.xcoinpay.pizza.pizzawallet.presenter.AddressPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;

public class AddressBookActivity extends BaseActivity<AddressPresenter> {
    @BindView(R.id.addressbook_recycleview)
    RecyclerView book_recycleview;

    ArrayList<BookInfo> bookInfos = null;
    private AddBookAdapter adapter;

    @Override
    public AddressPresenter getPresnter() {
        return new AddressPresenter();
    }

    @Override
    public void init() {
       setSupToolbar("地址薄",R.mipmap.ic_launcher);
       initBookInfo();

       book_recycleview.setLayoutManager(new LinearLayoutManager(this));
       //设置列表中的数据
        adapter = new AddBookAdapter(bookInfos);
        book_recycleview.setAdapter(adapter);
       //点击挑条目

        book_recycleview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),TransferActivity.class));//跳转到传送二维码页面
            }
        });

    }

    private void initBookInfo() {
        if(bookInfos==null){
            bookInfos = new ArrayList<>();
        }
        for(int i=0; i<20; i++){
            BookInfo bookInfo = new BookInfo("coin"+i,"name"+i,"address"+i);
            bookInfos.add(bookInfo);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dress_book;
    }

    //点击右边的按钮实现的逻辑
    @Override
    protected void onRightClick() {
//        Toast.makeText(this, "添加地址薄", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,AddAddressActivity.class));
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void onEvent(String string){

    }
}
