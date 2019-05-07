package com.example.administrator.stocktaking.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.stocktaking.MainActivity;
import com.example.administrator.stocktaking.R;
import com.example.administrator.stocktaking.ScanQRCodeActivity;
import com.example.administrator.stocktaking.Stocktake;
import com.example.administrator.stocktaking.bean.ListBean;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.List;

/**
 * Created by Administrator on 2019/5/4.
 */

public class RecyclerViewAdapter_HistoryList extends RecyclerView.Adapter<RecyclerViewAdapter_HistoryList.ViewHolder>{
    private List<ListBean> list;
    private Context context;

    public RecyclerViewAdapter_HistoryList(List<ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        RecyclerViewAdapter_HistoryList.ViewHolder viewHolder = new RecyclerViewAdapter_HistoryList.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtName.setText(list.get(position).getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "我被点击了", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.setClass(context, ScanQRCodeActivity.class);

                intent.putExtra("filepath",list.get(position).getName());
                context.startActivity(intent);


            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     *
     */
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtName;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tv_name);
        }
    }
}
