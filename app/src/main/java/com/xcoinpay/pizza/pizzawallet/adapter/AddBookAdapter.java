package com.xcoinpay.pizza.pizzawallet.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.bean.BookInfo;
import com.xcoinpay.pizza.pizzawallet.bean.Event;
import com.xcoinpay.pizza.pizzawallet.view.AddressBookActivity;
import com.xcoinpay.pizza.pizzawallet.view.TransferActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by llq on 2017/12/27 0027.
 */

public class AddBookAdapter extends RecyclerView.Adapter<AddBookAdapter.Hodler> {

    private  AddressBookActivity addressBookActivity;
    private  ArrayList<BookInfo> bookInfos;

    public AddBookAdapter(AddressBookActivity addressBookActivity, ArrayList<BookInfo> bookInfos) {
        this.bookInfos = bookInfos;
        this.addressBookActivity = addressBookActivity;
    }

    @Override
    public Hodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addbook_item, null);
        return new Hodler(view);
    }

    @Override
    public void onBindViewHolder(Hodler holder, final int position) {

        holder.coin.setText(bookInfos.get(position).getCoin()+"--");
        holder.name.setText(bookInfos.get(position).getName());
        holder.address.setText(bookInfos.get(position).getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(v.getContext(),TransferActivity.class);
                intent.putExtra("bookinfo",bookInfos.get(position));
                addressBookActivity.startActivity(intent);//跳转到传送二维码页面

            }
        });
    }

    @Override
    public int getItemCount() {
        return bookInfos.size();
    }

    public class Hodler extends RecyclerView.ViewHolder{
        @BindView(R.id.book_coin)
        TextView coin;
        @BindView(R.id.book_name)
        TextView name;
        @BindView(R.id.book_address)
        TextView address;
        View itemView;

        public Hodler(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this,itemView);
        }
    }
}
