package com.xcoinpay.pizza.pizzawallet.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcoinpay.pizza.pizzawallet.R;
import com.xcoinpay.pizza.pizzawallet.view.TradeBookActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by llq on 2018/1/3 0003.
 */

public class TradeBookAdapter extends RecyclerView.Adapter<TradeBookAdapter.Holder> {


    private TradeBookActivity tradeBookActivity;

    public TradeBookAdapter(TradeBookActivity tradeBookActivity) {
        this.tradeBookActivity = tradeBookActivity;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(tradeBookActivity).inflate(R.layout.trade_item, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.tvName.setText("比特币");
        holder.tvCount.setText("0.001");
        holder.tvId.setText("bit");
        holder.tvAddress.setText("FGFDGFHFGHGFHDSFGFGASDFASS");
        holder.tvTime.setText("2018-1-1 11:00");

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_id)
        TextView tvId;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.btn_detail)
        ImageView btnDetail;
        @BindView(R.id.tv_time)
        TextView tvTime;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
